package com.example.intouch;

public class User {

    private String userName;
    private int avatar;

    public User(String userName, int avatar) {
        this.userName = userName;
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPicture(int avatar) {
        this.avatar = avatar;
    }
}

