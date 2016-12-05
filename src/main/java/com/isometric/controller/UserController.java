package com.isometric.controller;

import com.isometric.GlobalConstants;
import com.isometric.IsometricBackEndApplication;
import com.isometric.MemcachedHelper;
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

    @CrossOrigin(origins = GlobalConstants.origin)
    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public Boolean authenticateUser(@PathVariable(value = "userId") BigInteger userId) {
        if (IsometricBackEndApplication.activeUsers.contains(userId))
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    @CrossOrigin(origins = GlobalConstants.origin)
    @RequestMapping(value = "/logout/{userId}", method = RequestMethod.POST)
    public void logout(@PathVariable(value = "userId") BigInteger userId) {
        IsometricBackEndApplication.activeUsers.remove(userId);
        /*System.out.println("Active Users' ID: ");
        for (BigInteger u : IsometricBackEndApplication.activeUsers) {
            System.out.println(u);
        }*/
    }

    @CrossOrigin(origins = GlobalConstants.origin)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse login(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password, @RequestParam(value = "time") String time, @RequestParam(value = "date") String date, @RequestParam(value = "location") String location) {
        if (userRepository.findByUserName(userName) != null) {
            if (MemcachedHelper.getFromCache(userName) != null)
                user = (User) MemcachedHelper.getFromCache(userName);
            else
                user = userRepository.findByUserName(userName);
            if (user.getPassword().equals(password)) {
                user.setPreviousLoginTime(user.getCurrentLoginTime());
                user.setPreviousLoginDate(user.getCurrentLoginDate());
                user.setPreviousLoginLocation(user.getCurrentLoginLocation());
                user.setCurrentLoginTime(time);
                user.setCurrentLoginDate(date);
                user.setCurrentLoginLocation(location);
                userRepository.save(user);
                MemcachedHelper.putInCache(userName, user);
                //System.out.println("Active Users' ID: ");
                IsometricBackEndApplication.activeUsers.add(user.getUserId());
                /*for (BigInteger u : IsometricBackEndApplication.activeUsers) {
                    System.out.println(u);
                }*/
                MemcachedHelper.removeFromCache(user.getUserId().toString());
                return new LoginResponse(user.getUserId(), "Login successful.");
            } else {
                return new LoginResponse("Incorrect password.");
            }
        } else {
            return new LoginResponse("Incorrect username.");
        }
    }

    @CrossOrigin(origins = GlobalConstants.origin)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RegisterResponse register(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password, @RequestParam(value = "email") String email, @RequestParam(value = "fullName") String fullName) {
        if (userRepository.findByUserName(userName) != null) {
            return new RegisterResponse("Registration failed. Username already exists.");
        } else if (userRepository.findByEmail(email) != null) {
            return new RegisterResponse("Registration failed. Email Address already used.");
        } else {
            userRepository.save(new User(getUserId(), userName, password, email, fullName));
            return new RegisterResponse("Registration successful.");
        }
    }

    @CrossOrigin(origins = GlobalConstants.origin)
    @RequestMapping(value = "/sso")
    public LoginResponse singleSignOn(@RequestParam(value = "email") String email, @RequestParam(value = "fullName") String fullName, @RequestParam(value = "time") String time, @RequestParam(value = "date") String date, @RequestParam(value = "location") String location) {
        if (MemcachedHelper.getFromCache(email) != null)
            user = (User) MemcachedHelper.getFromCache(email);
        else
            user = userRepository.findByUserName(email);
        if (userRepository.findByEmail(email) != null) {
            user = userRepository.findByEmail(email);
            user.setPreviousLoginTime(user.getCurrentLoginTime());
            user.setPreviousLoginDate(user.getCurrentLoginDate());
            user.setPreviousLoginLocation(user.getCurrentLoginLocation());
            user.setCurrentLoginTime(time);
            user.setCurrentLoginDate(date);
            user.setCurrentLoginLocation(location);
            userRepository.save(user);
            user = userRepository.findByEmail(email);
            MemcachedHelper.putInCache(email, user);
            IsometricBackEndApplication.activeUsers.add(user.getUserId());
            /*System.out.println("Active Users' ID: ");
            for (BigInteger u : IsometricBackEndApplication.activeUsers) {
                System.out.println(u);
            }*/
            MemcachedHelper.removeFromCache(user.getUserId().toString());
            return new LoginResponse(user.getUserId(), "Login successful.");
        } else {
            userRepository.save(new User(getUserId(), email, fullName));
            user = userRepository.findByEmail(email);
            MemcachedHelper.putInCache(email, user);
            IsometricBackEndApplication.activeUsers.add(user.getUserId());
            /*System.out.println("Active Users' ID: ");
            for (BigInteger u : IsometricBackEndApplication.activeUsers) {
                System.out.println(u);
            }*/
            return new LoginResponse(user.getUserId(), "Login successful.");
        }
    }

    @CrossOrigin(origins = GlobalConstants.origin)
    @RequestMapping(value = "/access/{userId}", method = RequestMethod.GET)
    public User userAccess(@PathVariable(value = "userId") BigInteger userId) {
        if (MemcachedHelper.getFromCache(userId.toString()) != null)
            user = (User) MemcachedHelper.getFromCache(userId.toString());
        else {
            user = userRepository.findOne(userId);
            MemcachedHelper.putInCache(userId.toString(), user);
        }
        return user;
    }

    @CrossOrigin(origins = GlobalConstants.origin)
    @RequestMapping(value = "/access/{userId}/update", method = RequestMethod.POST)
    public User userUpdate(@PathVariable(value = "userId") BigInteger userId, @RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password, @RequestParam(value = "email") String email, @RequestParam(value = "fullName") String fullName) {
        user = userRepository.findOne(userId);
        if (password != "") {
            if (user.getUserName().equals(userName) && user.getEmail().equals(email)) {
                user.setFullName(fullName);
                user.setPassword(password);
                userRepository.save(user);
            } else if (user.getUserName().equals(userName)) {
                if (userRepository.findByEmail(email) != null) {

                } else {
                    user.setEmail(email);
                }
                user.setFullName(fullName);
                user.setPassword(password);
                userRepository.save(user);
            } else if (user.getEmail().equals(email)) {
                if (userRepository.findByUserName(userName) != null) {

                } else {
                    user.setUserName(userName);
                }
                user.setFullName(fullName);
                user.setPassword(password);
                userRepository.save(user);
            } else {
                if (userRepository.findByUserName(userName) != null) {
                    if (userRepository.findByEmail(email) != null) {

                    } else {
                        user.setEmail(email);
                    }
                    user.setFullName(fullName);
                    user.setPassword(password);
                    userRepository.save(user);
                } else {
                    user.setUserName(userName);
                    if (userRepository.findByEmail(email) != null) {

                    } else {
                        user.setEmail(email);
                    }
                    user.setFullName(fullName);
                    user.setPassword(password);
                    userRepository.save(user);
                }
            }
        }
        MemcachedHelper.removeFromCache(userId.toString());
        MemcachedHelper.putInCache(userId.toString(), user);
        return user;
    }
}
