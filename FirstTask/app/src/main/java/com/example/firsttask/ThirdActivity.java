package com.example.firsttask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.firsttask.databinding.ActivityThirdBinding;

public class ThirdActivity extends AppCompatActivity {

    ActivityThirdBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThirdBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.SaveInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReturnToSecond(v);
            }
        });
    }

    protected void ReturnToSecond(View view){
        Intent intent = new Intent();
        String[] info = new String[2];
        info[0] = binding.DayEditText.getText().toString();
        info[1] = binding.TimeEditText.getText().toString();
        intent.putExtra(SecondActivity.INFO, info);
        setResult(RESULT_OK, intent);
        finish();

    }

}