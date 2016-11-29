package com.isometric.controller;

import com.isometric.entity.ID;
import com.isometric.entity.LoginResponse;
import com.isometric.entity.RegisterResponse;
import com.isometric.entity.User;
import com.isometric.repository.IDRepository;
import com.isometric.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping(value = "/isometric")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    private User user;

    @Autowired
    private IDRepository idRepository;
    private ID id;

    private BigInteger getUserId() {
        BigInteger userId;
        id = idRepository.findOne("key");
        userId = id.getUserId();
        id.setUserId(userId.add(BigInteger.valueOf(1)));
        idRepository.save(id);
        return userId;
    }

    @CrossOrigin(origins = "http://localhost:63343")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse login(@RequestParam(value = "login-username") String userName, @RequestParam(value = "login-password") String password, @RequestParam(value = "login-time") String time, @RequestParam(value = "login-date") String date, @RequestParam(value = "login-location") String location) {
        if (userRepository.findByUserName(userName) != null) {
            user = userRepository.findByUserName(userName);
            if (user.getPassword().equals(password)) {
                user.setPreviousLoginTime(user.getCurrentLoginTime());
                user.setPreviousLoginDate(user.getCurrentLoginDate());
                user.setPreviousLoginLocation(user.getCurrentLoginLocation());
                user.setCurrentLoginTime(time);
                user.setCurrentLoginDate(date);
                user.setCurrentLoginLocation(location);
                userRepository.save(user);
                return new LoginResponse("Login successful.");
            } else {
                return new LoginResponse("Incorrect password.");
            }
        } else {
            return new LoginResponse("Incorrect username.");
        }
    }

    @CrossOrigin(origins = "http://localhost:63343")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RegisterResponse register(@RequestParam(value = "register-username") String userName, @RequestParam(value = "register-password") String password, @RequestParam(value = "register-email") String email, @RequestParam(value = "register-fullname") String fullName) {
        if (userRepository.findByUserName(userName) != null) {
            return new RegisterResponse("Registration failed. Username already exists.");
        } else if (userRepository.findByEmail(email) != null) {
            return new RegisterResponse("Registration failed. Email Address already used.");
        } else {
            userRepository.save(new User(getUserId(), userName, password, email, fullName));
            //userRepository.save(new User(userName, password, email, fullName));
            return new RegisterResponse("Registration successful.");
        }
    }

    @CrossOrigin(origins = "http://localhost:63343")
    @RequestMapping(value = "/access/{user}", method = RequestMethod.POST)
    public User userAccess(@PathVariable(value = "user") String userName) {
        user = userRepository.findByUserName(userName);
        return user;
    }

    /*@RequestMapping(value = "/access/{user}/update", method = RequestMethod.POST)
    public User userUpdate(@PathVariable(value = "user") String userToken, @RequestParam(value = "profile-modal-username") String userName, @RequestParam(value = "profile-modal-password") String password, @RequestParam(value = "profile-modal-email") String email, @RequestParam(value = "profile-modal-fullname") String fullName) {
        if(userToken.equals(userName) && userRepository.findByUserName(userToken).getEmail().equals(email)){
            user = userRepository.findByUserName(userName);
        }

        return user;
    }*/
}
