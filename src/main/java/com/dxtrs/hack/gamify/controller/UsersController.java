package com.dxtrs.hack.gamify.controller;

import com.dxtrs.hack.gamify.model.User;
import com.dxtrs.hack.gamify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/all-users")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
    @RequestMapping("/add-user")
    public User addNewUser(User user) {
        return userRepository.save(user);
    }
}
