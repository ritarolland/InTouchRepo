package com.example.intouch.models;

import java.util.Date;

public class ChatMessage {
    private String senderId, receiverId, dateTime, message;
    private Date dateObject;
    public String conversionId, conversionName, conversationImage;

    public void setConversionId(String conversionId) {
        this.conversionId = conversionId;
    }

    public void setConversionName(String conversionName) {
        this.conversionName = conversionName;
    }

    public String getConversionId() {
        return conversionId;
    }

    public String getConversionName() {
        return conversionName;
    }

    public Date getDateObject() {
        return dateObject;
    }

    public void setDateObject(Date dateObject) {
        this.dateObject = dateObject;
    }

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
