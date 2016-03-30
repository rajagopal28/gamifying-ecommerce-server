package com.dxtrs.hack.gamify.repository;

import com.dxtrs.hack.gamify.model.Promotion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends CrudRepository<Promotion, Long>{

    Promotion findByPromotionCode(String promotionCode);

    List<Promotion> findByAppliedOnLevel(String appliedOnLevel);
}
