package com.nanox.w2m.domain;

public class UserLogin {

    private String username;
    private String token;

    public static UserLogin from(String username, String token) {
        UserLogin userLogin = new UserLogin();
        userLogin.setUsername(username);
        userLogin.setToken(token);
        return userLogin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}