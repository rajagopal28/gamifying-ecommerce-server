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
    String some = "select sum(ur.value) as value, FLOOR(10000 + RAND() * 89999) as id, ur.category as category, ur.user_id as user_id, rp.id as promotion_id"
            + " from user_rewards ur inner join reward_promotions rp on rp.upper_limit >= value and rp.lower_limit <= value"
            + "where ur.category=:categoryValue group by ur.user_id order by value desc ";
    @Query(value = "select res.value as value,  FLOOR(10000 + RAND() * 79999) as id, res.category as category, res.user_id as user_id, rp.id as promotion_id from "
            + " (select sum(value) as value, category, user_id  from user_rewards where category = :categoryValue group by user_id order by value desc) as res "
            + " left join reward_promotions rp on rp.category=res.category and rp.upper_limit >= res.value and rp.lower_limit <= res.value", nativeQuery = true)
    List<CumulativeReward> getCumulativeForCategory(@Param("categoryValue") String categoryValue);

    @Query(value = "select sum(value) as value, FLOOR(10000 + RAND() * 79999) as id, user_id as user_id, 'All' as category, null as promotion_id  from user_rewards group by user_id order by value desc", nativeQuery = true)
    List<CumulativeReward> getCumulativeByUser();

    @Query(value = "SELECT DISTINCT(ur.category) FROM user_rewards ur", nativeQuery = true)
    List<String> getAllCategories();
}
