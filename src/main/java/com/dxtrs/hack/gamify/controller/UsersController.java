package com.dxtrs.hack.gamify.controller;

import com.dxtrs.hack.gamify.model.User;
import com.dxtrs.hack.gamify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

@RestController
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/all-users")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value="/add-user", method= RequestMethod.GET)
    public @ResponseBody User addNewUser(@RequestParam(value = "firstName", required = true) String firstName,
                                         @RequestParam(value = "lastName", required = false) String lastName,
                                         @RequestParam(value = "dob", required = true) String dob,
                                         @RequestParam(value = "state", required = true) String state,
                                         @RequestParam(value = "wallet", required = true) Long wallet,
                                         @RequestParam(value = "currencyType", required = true) String currencyType,
                                         @RequestParam(value = "city", required = true) String city) throws ParseException{
        User user = new User();
        user.setCity(city);
        user.setCurrencyType(currencyType);
        DateFormat sf = new SimpleDateFormat("dd-mm-yyyy");
        Calendar c = new GregorianCalendar();
        user.setDob(new Date(sf.parse(dob).getTime()));
        user.setCreatedTS(new Date(c.getTimeInMillis()));
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setState(state);
        user.setWallet(wallet);
        System.out.println(user);
        return userRepository.save(user);
    }
}
