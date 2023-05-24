package com.example.intouch.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.intouch.R;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;

public class User implements Serializable {


    private String userEmail, userPassword, userName, id;
    private int avatar = R.drawable.logo;
    private boolean sex = true;

    public User(String id, String userEmail, String userPassword, String userName) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
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

    /*@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(userEmail);
        dest.writeString(userName);
        dest.writeString(userPassword);
        dest.writeString(id);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            dest.writeBoolean(sex);
        }
        dest.writeInt(avatar);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private User(Parcel in) {
        avatar = in.readInt();
        userEmail = in.readString();
        userName = in.readString();
        userPassword = in.readString();
        id = in.readString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            sex = in.readBoolean();
        }
    }*/
}

