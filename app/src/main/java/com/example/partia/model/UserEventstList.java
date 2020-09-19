package com.example.partia.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.List;
import java.util.List;


public class UserEventstList {

    @SerializedName("owner")
    @Expose
    private ArrayList<Event> owner = null;
    @SerializedName("participates")
    @Expose
    private ArrayList<Event> participates = null;

    public ArrayList<Event> getOwner() {
        return owner;
    }

    public void setOwner(ArrayList<Event> owner) {
        this.owner = owner;
    }

    public ArrayList<Event> getParticipates() {
        return participates;
    }

    public void setParticipates(ArrayList<Event> participates) {
        this.participates = participates;
    }

}