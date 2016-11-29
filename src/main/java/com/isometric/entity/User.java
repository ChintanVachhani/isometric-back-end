package com.isometric.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import java.math.BigInteger;

public class User {
    @Id
    private BigInteger userId;
    private String userName;
    @JsonIgnore
    private String password;
    private String email;
    private String fullName;
    private String previousLoginTime;
    private String previousLoginDate;
    private String previousLoginLocation;
    private String currentLoginTime;
    private String currentLoginDate;
    private String currentLoginLocation;

    public User() {
    }

    public User(BigInteger userId, String userName, String password, String email, String fullName) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPreviousLoginTime() {
        return previousLoginTime;
    }

    public void setPreviousLoginTime(String previousLoginTime) {
        this.previousLoginTime = previousLoginTime;
    }

    public String getPreviousLoginDate() {
        return previousLoginDate;
    }

    public void setPreviousLoginDate(String previousLoginDate) {
        this.previousLoginDate = previousLoginDate;
    }

    public String getPreviousLoginLocation() {
        return previousLoginLocation;
    }

    public void setPreviousLoginLocation(String previousLoginLocation) {
        this.previousLoginLocation = previousLoginLocation;
    }

    public String getCurrentLoginTime() {
        return currentLoginTime;
    }

    public void setCurrentLoginTime(String currentLoginTime) {
        this.currentLoginTime = currentLoginTime;
    }

    public String getCurrentLoginDate() {
        return currentLoginDate;
    }

    public void setCurrentLoginDate(String currentLoginDate) {
        this.currentLoginDate = currentLoginDate;
    }

    public String getCurrentLoginLocation() {
        return currentLoginLocation;
    }

    public void setCurrentLoginLocation(String currentLoginLocation) {
        this.currentLoginLocation = currentLoginLocation;
    }
}
