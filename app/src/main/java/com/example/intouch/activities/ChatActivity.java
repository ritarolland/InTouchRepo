package com.example.intouch.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.intouch.databinding.ActivityChatBinding;
import com.example.intouch.databinding.ActivityChatCreateBinding;

public class ChatActivity extends AppCompatActivity {
    ActivityChatBinding activityChatBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChatBinding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(activityChatBinding.getRoot());
    }
}