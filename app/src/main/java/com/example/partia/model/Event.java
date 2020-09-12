package com.example.partia.model;

import com.google.gson.annotations.SerializedName;

public class Event {
    @SerializedName("name")
    public String name;
    @SerializedName("owner")
    public String owner;
    @SerializedName("state")
    public String state;
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


    public Event(String name, String owner, String beverage_organization, String location, String info, String environment, String kind_of_event, String date, String meal_organization) {
        this.name = name;
        this.owner = owner;
        this.beverage_organization = beverage_organization;
        this.location = location;
        this.info = info;
        this.environment = environment;
        this.kind_of_event = kind_of_event;
        this.date = date;
        this.meal_organization = meal_organization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeverage_organization() {
        return beverage_organization;
    }

    public void setBeverage_organization(String beverage_organization) {
        this.beverage_organization = beverage_organization;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getKind_of_event() {
        return kind_of_event;
    }

    public void setKind_of_event(String kind_of_event) {
        this.kind_of_event = kind_of_event;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMeal_organization() {
        return meal_organization;
    }

    public void setMeal_organization(String meal_organization) {
        this.meal_organization = meal_organization;
    }

    public Integer getPin_code() {
        return pin_code;
    }

    public void setPin_code(Integer pin_code) {
        this.pin_code = pin_code;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", state='" + state + '\'' +
                ", beverage_organization='" + beverage_organization + '\'' +
                ", location='" + location + '\'' +
                ", info='" + info + '\'' +
                ", environment='" + environment + '\'' +
                ", kind_of_event='" + kind_of_event + '\'' +
                ", date='" + date + '\'' +
                ", meal_organization='" + meal_organization + '\'' +
                ", pin_code=" + pin_code +
                '}';
    }
}
