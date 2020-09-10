package com.example.partia.model;

public class LoginRequest  {
    private String userEmail;
    private String password;

    public LoginRequest(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }

    public LoginRequest() {

    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
