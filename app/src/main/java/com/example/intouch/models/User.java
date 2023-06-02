package com.example.intouch.models;

import com.example.intouch.R;

import java.io.Serializable;

public class User implements Serializable {


    public String userEmail, userPassword, userName, id;
    private int avatar = R.drawable.logo;
    private boolean sex = true;
    public String profileImage = null;

    public User(){};

    public User(String id, String userEmail, String userPassword, String userName) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.id = id;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public boolean isSex() {
        return sex;
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

}

