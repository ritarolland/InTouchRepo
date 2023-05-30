package com.example.intouch.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.intouch.databinding.ActivityHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {
    ActivityHomeBinding activityHomeBinding;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = activityHomeBinding.getRoot();
        setContentView(view);
        init();
    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void onClickSignOut(View view) {
        mAuth.signOut();
        startActivity(new Intent(this, AuthActivity.class));
    }

    public void onClickMessenger(View view) {
        startActivity(new Intent(this, MessengerActivity.class));
    }

}