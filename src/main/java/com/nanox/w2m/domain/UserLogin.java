package com.nanox.w2m.domain;

public class UserLogin {

    private final String username;
    private final String token;

    public UserLogin(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

}