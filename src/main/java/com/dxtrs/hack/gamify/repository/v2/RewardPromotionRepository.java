package com.dxtrs.hack.gamify.repository.v2;

import com.dxtrs.hack.gamify.model.v2.RewardPromotion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardPromotionRepository extends CrudRepository<RewardPromotion, Long> {

    List<RewardPromotion> findByCategory(String category);

    List<RewardPromotion> findAllByOrderByCreatedTSDesc();

    @Query(value = "select * from reward_promotions where category = :categoryValue and upper_limit >= :rewardValue and lower_limit <= :rewardValue", nativeQuery = true)
    RewardPromotion getPromotionForCategoryWithPoints(@Param("categoryValue") String categoryValue, @Param("rewardValue") Double rewardValue);
}
