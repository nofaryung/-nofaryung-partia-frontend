package com.example.partia.model;

import com.google.gson.annotations.SerializedName;

public class Event {
    @SerializedName("name")
    public String name;
    @SerializedName("beverage_organization")
    public String beverage_organization;
    @SerializedName("location")
    public String location;
    @SerializedName("info")
    public String info;
    @SerializedName("environment")
    public String environment;
    @SerializedName("kind_of_event")
    public String kind_of_event;
    @SerializedName("date")
    public String date;
    @SerializedName("meal_organization")
    public String meal_organization;
    @SerializedName("pin_code")
    public Integer pin_code;


    public Event(String name, String beverage_organization, String location, String info, String environment, String kind_of_event, String date, String meal_organization) {
        this.name = name;
        this.beverage_organization = beverage_organization;
        this.location = location;
        this.info = info;
        this.environment = environment;
        this.kind_of_event = kind_of_event;
        this.date = date;
        this.meal_organization = meal_organization;
    }
}
