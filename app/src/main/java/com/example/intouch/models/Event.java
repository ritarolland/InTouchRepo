package com.example.intouch.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event implements Serializable {
    public Date dateObject;
    public String eventName, eventDescription, eventCategory, date, time, creatorId, eventId;
    public boolean onlyGirls = false, onlyBoys = false;
    public int ageMin, ageMax, numbMin, numbMax;
    public List<String> members = new ArrayList<>();
}
