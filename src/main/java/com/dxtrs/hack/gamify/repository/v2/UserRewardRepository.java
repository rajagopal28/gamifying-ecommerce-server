package com.dxtrs.hack.gamify.repository.v2;

import com.dxtrs.hack.gamify.model.User;
import com.dxtrs.hack.gamify.model.v2.UserReward;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRewardRepository extends CrudRepository<UserReward, Long> {
    List<UserReward> findAllByOrderByCreatedTSDesc();

    List<UserReward> findByUserOrderByCreatedTSDesc(User user);

    List<UserReward> findByCategoryOrderByCreatedTSDesc(String category);

    UserReward findByUserAndCategory(User user, String category);
}
