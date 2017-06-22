package com.dxtrs.hack.gamify.controller.v2;

import com.dxtrs.hack.gamify.model.User;
import com.dxtrs.hack.gamify.model.v2.CumulativeReward;
import com.dxtrs.hack.gamify.model.v2.UserReward;
import com.dxtrs.hack.gamify.repository.UserRepository;
import com.dxtrs.hack.gamify.repository.v2.CumulativeRewardRepository;
import com.dxtrs.hack.gamify.repository.v2.UserRewardRepository;
import com.dxtrs.hack.gamify.util.GamifierUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserRewardsController {

    private static final String defaultCategory = "All";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRewardRepository userRewardRepository;

    @Autowired
    private CumulativeRewardRepository cumulativeRewardRepository;

    @RequestMapping(value = "/api/v2/user-rewards/add", method = RequestMethod.POST)
    public UserReward addCategoryRewardForUser(@RequestParam(value = "category", required = true) String category,
                                               @RequestParam(value = "userId", required = true) Long userId,
                                               @RequestParam(value = "rewardPoints", defaultValue = "0.0") Double rewardPoints) {

        User user = userRepository.findOne(userId);

        UserReward reward = new UserReward();
        reward.setValue(rewardPoints);
        reward.setUser(user);
        Date now = GamifierUtil.getCurrentDate();
        reward.setCreatedTS(now);
        reward.setLastUpdatedTs(now);
        reward.setCategory(category);

        return userRewardRepository.save(reward);
    }

    @RequestMapping(value = "/api/v2/user-rewards/all", method = RequestMethod.GET)
    public Iterable<UserReward> getRewardsOfUser() {
        return userRewardRepository.findAllByOrderByCreatedTSDesc();
    }

    @RequestMapping(value = "/api/v2/user-rewards/for-user", method = RequestMethod.GET)
    public Iterable<UserReward> getRewardsOfUser(@RequestParam(value = "userId", required = true) Long userId) {
        User user = userRepository.findOne(userId);

        return userRewardRepository.findByUserOrderByCreatedTSDesc(user);
    }

    @RequestMapping(value = "/api/v2/user-rewards/for-category", method = RequestMethod.GET)
    public Iterable<UserReward> getRewardsOfCategory(@RequestParam(value = "category", required = true) String category) {
        return userRewardRepository.findByCategoryOrderByCreatedTSDesc(category);
    }


    @RequestMapping(value = "/api/v2/user-rewards/cumulative", method = RequestMethod.GET)
    public Iterable<CumulativeReward> getCumulativeRewardsOfCategory(@RequestParam(value = "category", required = false, defaultValue = defaultCategory) String category) {
        if (defaultCategory.equalsIgnoreCase(category)) {
            return cumulativeRewardRepository.getCumulativeByUser();
        } else {
            return cumulativeRewardRepository.getCumulativeForCategory(category);
        }
    }

    @RequestMapping("/api/v2/categories")
    public List<String> getCategoryList() {
        return cumulativeRewardRepository.getAllCategories();
    }
}
