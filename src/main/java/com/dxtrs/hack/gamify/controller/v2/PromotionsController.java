package com.dxtrs.hack.gamify.controller.v2;

import com.dxtrs.hack.gamify.model.v2.RewardPromotion;
import com.dxtrs.hack.gamify.repository.v2.RewardPromotionRepository;
import com.dxtrs.hack.gamify.util.GamifierUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@CrossOrigin(origins = "*")
public class PromotionsController {

    @Autowired
    private RewardPromotionRepository rewardPromotionRepository;

    @RequestMapping(value = "/api/v2/promotions/add", method = RequestMethod.POST)
    public RewardPromotion orderProducts(@ModelAttribute() RewardPromotion promotion) {
        Date now = GamifierUtil.getCurrentDate();
        promotion.setCreatedTS(now);
        promotion.setLastUpdatedTS(now);

        return rewardPromotionRepository.save(promotion);
    }

    @RequestMapping(value = "/api/v2/promotions/all", method = RequestMethod.GET)
    public Iterable<RewardPromotion> getAllPromotions() {
        return rewardPromotionRepository.findAllByOrderByCreatedTSDesc();
    }

}
