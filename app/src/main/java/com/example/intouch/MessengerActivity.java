package com.example.intouch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.intouch.databinding.ActivityMessengerBinding;

import java.util.ArrayList;

public class MessengerActivity extends AppCompatActivity {
    ActivityMessengerBinding activityMessengerBinding;
    ArrayList<User> chats = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMessengerBinding = ActivityMessengerBinding.inflate(getLayoutInflater());
        View view = activityMessengerBinding.getRoot();
        setContentView(view);
        setInitialData();
        RecyclerView recyclerView = activityMessengerBinding.chats;
        MessengerAdapter messengerAdapter = new MessengerAdapter(this.getLayoutInflater(), chats);
        recyclerView.setAdapter(messengerAdapter);
    }

    private void setInitialData(){
        for(int i = 0; i < 5; i++) {
            chats.add(new User ("Вероника", R.drawable.logo));
            chats.add(new User ("Соня", R.drawable.logo));
            chats.add(new User ("Дядя Федор", R.drawable.logo));
            chats.add(new User ("Вероника", R.drawable.logo));
            chats.add(new User ("Лиза", R.drawable.logo));
        }


    }
}