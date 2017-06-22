package com.dxtrs.hack.gamify.repository.v2;

import com.dxtrs.hack.gamify.model.User;
import com.dxtrs.hack.gamify.model.v2.CumulativeReward;
import com.dxtrs.hack.gamify.model.v2.UserReward;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CumulativeRewardRepository extends CrudRepository<CumulativeReward, Long> {
    List<CumulativeReward> findByUser(User user);

    List<CumulativeReward> findByCategory(String category);

    UserReward findByUserAndCategory(User user, String category);

    @Query(value = "select res.value as value,  FLOOR(10000 + RAND() * 79999) as id, res.category as category, res.user_id as user_id, rp.id as promotion_id from "
            + " (select sum(value) as value, category, user_id  from user_rewards where category = :categoryValue group by user_id) as res "
            + " left join reward_promotions rp on rp.category=res.category and rp.upper_limit >= res.value and rp.lower_limit <= res.value order by res.value desc", nativeQuery = true)
    List<CumulativeReward> getCumulativeForCategory(@Param("categoryValue") String categoryValue);

    @Query(value = "select res.value as value,  FLOOR(10000 + RAND() * 79999) as id, 'All' as category, res.user_id as user_id, rp.id as promotion_id from "
            + " (select sum(value) as value, user_id  from user_rewards group by user_id) as res "
            + " left join reward_promotions rp on rp.category='All' and rp.upper_limit >= res.value and rp.lower_limit <= res.value order by res.value desc", nativeQuery = true)
    List<CumulativeReward> getCumulativeByUser();

    @Query(value = "SELECT DISTINCT(ur.category) FROM user_rewards ur", nativeQuery = true)
    List<String> getAllCategories();
}
