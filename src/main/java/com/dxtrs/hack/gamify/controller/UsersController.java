package com.dxtrs.hack.gamify.controller;

import com.dxtrs.hack.gamify.model.User;
import com.dxtrs.hack.gamify.repository.UserRepository;
import com.dxtrs.hack.gamify.util.GamifierUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;

@RestController
@CrossOrigin(origins = "*")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/api/users/all")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/api/users/add", method = RequestMethod.POST)
    public
    @ResponseBody
    User addNewUser(@ModelAttribute() User user) throws ParseException {
        user.setDob(GamifierUtil.convertDateFromString(user.getDateOfBirth()));
        Date now = GamifierUtil.getCurrentDate();
        user.setCreatedTS(now);
        user.setLastUpdatedTS(now);

        return userRepository.save(user);
    }
}
