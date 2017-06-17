package com.dxtrs.hack.gamify.controller.v2;

import com.dxtrs.hack.gamify.dto.v2.PaymentRequestDTO;
import com.dxtrs.hack.gamify.model.User;
import com.dxtrs.hack.gamify.model.v2.Payment;
import com.dxtrs.hack.gamify.model.v2.RewardPromotion;
import com.dxtrs.hack.gamify.model.v2.UserReward;
import com.dxtrs.hack.gamify.repository.UserRepository;
import com.dxtrs.hack.gamify.repository.v2.PaymentRepository;
import com.dxtrs.hack.gamify.repository.v2.RewardPromotionRepository;
import com.dxtrs.hack.gamify.repository.v2.UserRewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class PaymentsController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RewardPromotionRepository rewardPromotionRepository;

    @Autowired
    private UserRewardRepository userRewardRepository;

    @RequestMapping(value = "/api/payments/add", method = RequestMethod.POST)
    public Payment addPayment(@ModelAttribute() PaymentRequestDTO paymentRequest) {

        User user = userRepository.findOne(paymentRequest.getUserId());
        Payment payment = paymentRequest.getPayment(user);

        UserReward existingReward = userRewardRepository.findByUserAndCategory(payment.getUser(), payment.getCategory());
        RewardPromotion promotion = rewardPromotionRepository.getPromotionForCategoryWithPoints(payment.getCategory(), existingReward.getValue());

        applyPromotionsForNewPayment(payment, promotion);

        UserReward updatedReward = updateRewardPointsAndPromotionsForPayment(payment, existingReward, promotion);
        userRewardRepository.save(updatedReward);

        return paymentRepository.save(payment);
    }

    @RequestMapping(value = "/api/payments/all", method = RequestMethod.GET)
    public Iterable<Payment> getPaymentsByCustomer(@RequestParam(value = "userId", required = true) Long userId) {
        User user = new User();
        user.setId(userId);
        return paymentRepository.findByUser(user);
    }

    private Payment applyPromotionsForNewPayment(Payment payment, RewardPromotion promotion) {
        if (promotion != null) {
            Double amount = payment.getAmount();
            amount *= (1 - promotion.getDiscount()); // <== discount is always a X/100 value
            payment.setAmount(amount);

            payment.setPromotion(promotion);
        }
        return payment;
    }

    private UserReward updateRewardPointsAndPromotionsForPayment(Payment payment, UserReward reward, RewardPromotion promotion) {
        Double amount = payment.getAmount();
        Double existingPoints = reward.getValue();
        Double promotionalMultiplier = promotion != null ? promotion.getRewardMultiplier() : 1;
        Double currentPoints = promotionalMultiplier * amount / 2; // <== current logic is reward point is half the value of purchase amount

        reward.setValue(existingPoints + currentPoints);
        return reward;
    }

//    @RequestMapping(value = "/api/payments/users/count", method = RequestMethod.GET)
//    public List<ChartData> getOrdersByUsersCount() {
//        return chartDataRepository.countOfOrdersByUsers();
//    }
//
//    @RequestMapping(value = "/api/payments/categories/count", method = RequestMethod.GET)
//    public List<ChartData> getOrdersByCategoryCount() {
//        return chartDataRepository.countOfOrdersByCategory();
//    }

}
