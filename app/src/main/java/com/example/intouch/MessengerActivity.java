package com.example.intouch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.intouch.databinding.ActivityMainBinding;
import com.example.intouch.databinding.ActivityMessengerBinding;

public class MessengerActivity extends AppCompatActivity {
    ActivityMessengerBinding activityMessengerBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMessengerBinding = ActivityMessengerBinding.inflate(getLayoutInflater());
        View view = activityMessengerBinding.getRoot();
        setContentView(view);

    }
}