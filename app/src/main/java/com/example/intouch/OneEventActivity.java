package com.example.intouch;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.intouch.databinding.ActivityAuthBinding;
import com.example.intouch.databinding.ActivityOneEventBinding;

public class OneEventActivity extends AppCompatActivity {



    ActivityOneEventBinding binding;

    ProgressBar progressbar;
    Button button;
    Handler handler;

    TextView NumberPeople;

    TextView EndPeopleOrNotText;

    TextView description;

    int max = 10;  //здесь максимальное количество человек


    private int progress = 6;              //здесь количество зарегавшихся на событие



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOneEventBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        int tumbler = 0;         //включена/выключена switch на экране AddEvent(0/1)



        handler = new Handler();

        String string;


        if (tumbler == 0){

            string = "" + progress + "/" + max;
            binding.EndPeopleOrNotText.setText("Ограниченное количество участников");
        }

        else{
            string = "" + progress;
            binding.EndPeopleOrNotText.setText("Неограниченное количество участников");

        }

        binding.DescriptionText.setText("rhgbkdfbvnmsdm,nzd,mfgbhgehbnmsnvsbgberhgbvnmszdnnmbdfhjgeryughsvnsabghjaehjgbam");



        binding.NumberPeople.setText(string);
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.progressBar.setMax(max);

        new Thread(new Runnable() {

            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.progressBar.setProgress(progress);

                    }
                });

            }
        }).start();




    }
}