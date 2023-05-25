package com.example.intouch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intouch.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void OnClick(View view){
        Intent intent = new Intent(MainActivity.this, ScrollingActivity.class);
        startActivity(intent);

    }
}