package com.example.partia.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("userEmail")
    private String userEmail;
    @SerializedName("password")
    private String password;

    public LoginResponse(String userId, String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
