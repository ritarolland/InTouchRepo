package com.example.intouch;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

import com.example.intouch.databinding.ActivityScrollingBinding;

public class ScrollingActivity extends AppCompatActivity {

    private ActivityScrollingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
//    public void OnClickNotification(View view){
//
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        Toast.makeText(this, "Going to Notification", Toast.LENGTH_SHORT).show();
//
//    }
}