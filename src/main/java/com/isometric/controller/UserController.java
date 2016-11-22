package com.isometric.controller;

import com.isometric.entity.User;
import com.isometric.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/isometric/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    private User user;

    @RequestMapping(value = "/status")
    public String checkConnection() {
        return "CONNECTED!";
    }

    @RequestMapping(value = "/login")
    public String login(@RequestParam(value = "login-username") String userName, @RequestParam(value = "login-password") String password) {
        if (userRepository.findByUserName(userName) != null) {
            user = userRepository.findByUserName(userName);
            if (user.getPassword().equals(password))
                return "LOGIN SUCCESSFUL!";
            else {
                return "INCORRECT PASSWORD!";
            }
        } else {
            return "INCORRECT USERNAME!";
        }
    }

    @RequestMapping(value = "/register")
    public String register(@RequestParam(value = "register-username") String userName, @RequestParam(value = "register-password") String password, @RequestParam(value = "register-email") String email, @RequestParam(value = "register-fullname") String fullName) {
        if (userRepository.findByUserName(userName) != null) {
            return "REGISTRATION FAILED! USERNAME ALREADY EXISTS!";
        } else if (userRepository.findByEmail(email) != null) {
            return "REGISTRATION FAILED! EMAIL ADDRESS ALREADY USED!";
        } else {
            userRepository.save(new User(userName, password, email, fullName));
            return "REGISTRATION SUCCESSFUL!";
        }
    }
}
