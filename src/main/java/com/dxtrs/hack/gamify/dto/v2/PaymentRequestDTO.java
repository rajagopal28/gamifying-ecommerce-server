package com.dxtrs.hack.gamify.dto.v2;

import com.dxtrs.hack.gamify.model.User;
import com.dxtrs.hack.gamify.model.v2.Payment;
import com.dxtrs.hack.gamify.util.GamifierUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDTO {
    private long id;
    private String description;
    private Double amount;
    private String category;
    private long userId;

    public Payment getPayment(User user) {
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setCategory(category);
        payment.setDescription(description);
        payment.setCreatedTS(GamifierUtil.getCurrentDate());
        payment.setUser(user);

        return payment;
    }
}
