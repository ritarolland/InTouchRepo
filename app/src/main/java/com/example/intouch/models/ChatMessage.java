package com.example.intouch.models;

import java.util.Date;

public class ChatMessage {
    private String senderId, receiverId, dateTime, message;
    //private Date dateObject;

    public ChatMessage(){}

    public ChatMessage(String senderId, String receiverId, String message, String dateTime) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.dateTime = dateTime;
        this.message = message;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
