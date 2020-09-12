package com.example.partia.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.security.acl.Owner;
import java.util.List;
import java.util.List;


public class UserEventstList {

    @SerializedName("owner")
    @Expose
    private List<Event> owner = null;
    @SerializedName("participates")
    @Expose
    private List<Event> participates = null;

    public List<Event> getOwner() {
        return owner;
    }

    public void setOwner(List<Event> owner) {
        this.owner = owner;
    }

    public List<Event> getParticipates() {
        return participates;
    }

    public void setParticipates(List<Event> participates) {
        this.participates = participates;
    }

}