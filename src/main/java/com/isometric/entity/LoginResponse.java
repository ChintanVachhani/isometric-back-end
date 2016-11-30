package com.isometric.entity;

import java.math.BigInteger;

public class LoginResponse {
    private String message;
    private String idToken;
    private BigInteger userId;

    public LoginResponse() {
    }

    public LoginResponse(BigInteger userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public LoginResponse(String message) {
        this.message = message;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public String getIdToken() {
        return idToken;
    }
}
