package com.example.intouch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.intouch.databinding.ActivityHomeBinding;
import com.google.firebase.auth.FirebaseAuth;

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

}