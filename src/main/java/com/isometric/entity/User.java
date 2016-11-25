package com.isometric.entity;

import org.springframework.data.annotation.Id;

/**
 * Created by gfogla on 11/25/2016.
 */
public class User {
    @Id
    private String userId;
    private String userName;
    private String password;
    private String email;
    private String fullName;
    private String lastLoginTime;

    public User() {
    }

    public User(String userName, String password, String email, String fullName, String lastLoginTime) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.lastLoginTime = lastLoginTime;
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

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                '}';
    }
}
