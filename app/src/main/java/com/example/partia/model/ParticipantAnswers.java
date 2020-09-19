package com.example.partia.model;

import com.google.gson.annotations.SerializedName;

public class ParticipantAnswers {
    @SerializedName("userEmail")
    public String userEmail;
    @SerializedName("pin_code")
    public Integer pin_code;
    @SerializedName("mealPreference")
    public String mealPreference;
    @SerializedName("allergies")
    public String allergies;
    @SerializedName("glassPreference")
    public String glassPreference;
    @SerializedName("chaserPreference")
    public String chaserPreference;

    public ParticipantAnswers(String userEmail, Integer pin_code) {
        this.userEmail = userEmail;
        this.pin_code = pin_code;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getMealPreference() {
        return mealPreference;
    }

    public void setMealPreference(String mealPreference) {
        this.mealPreference = mealPreference;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getGlassPreference() {
        return glassPreference;
    }

    public void setGlassPreference(String glassPreference) {
        this.glassPreference = glassPreference;
    }

    public String getChaserPreference() {
        return chaserPreference;
    }

    public void setChaserPreference(String chaserPreference) {
        this.chaserPreference = chaserPreference;
    }
}
