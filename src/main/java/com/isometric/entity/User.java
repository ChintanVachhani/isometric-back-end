package com.isometric.entity;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    private String id;
    private String userName;
    private String password;
    private String email;
    private String fullName;
    private LoginInformation lastSuccessfulLogin;

    public User(String userName, String password, String email, String fullName) {
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

    public LoginInformation getLastSuccessfulLogin() {
        return lastSuccessfulLogin;
    }

    public void setLastSuccessfulLogin(LoginInformation lastSuccessfulLogin) {
        this.lastSuccessfulLogin = lastSuccessfulLogin;
    }
}
