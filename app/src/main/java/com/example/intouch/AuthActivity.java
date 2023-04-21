package com.example.intouch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.intouch.databinding.ActivityAuthBinding;

public class AuthActivity extends AppCompatActivity {
    ActivityAuthBinding activityAuthBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAuthBinding = ActivityAuthBinding.inflate(getLayoutInflater());
        View view = activityAuthBinding.getRoot();
        setContentView(view);
    }
}