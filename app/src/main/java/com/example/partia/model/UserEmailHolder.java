package com.example.partia.model;

import com.google.gson.annotations.SerializedName;

public class UserEmailHolder {
    @SerializedName("userEmail")
    private String userEmail;

    public UserEmailHolder(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
