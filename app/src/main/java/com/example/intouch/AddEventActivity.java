package com.example.intouch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.intouch.databinding.ActivityAddEventBinding;
import com.example.intouch.databinding.ActivityMainBinding;

public class AddEventActivity extends AppCompatActivity {

    AppCompatButton womanButton;
    AppCompatButton manButton;
    AppCompatButton allButton;

    String[] item = {"Комфорт", "Спорт", "Вечеринки", "Саморазвитие", "Другое"};

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        autoCompleteTextView = findViewById(R.id.auto_complete_text);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, item);

        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Item = parent.getItemAtPosition(position).toString();

            }
        });




        womanButton = (AppCompatButton) findViewById(R.id.WomanButton);
        manButton = (AppCompatButton) findViewById(R.id.ManButton);
        allButton = (AppCompatButton) findViewById(R.id.AllButton);


    }
    public void ClickWoman(View view){
        womanButton.setBackgroundResource(R.drawable.woman_backgroud);
        manButton.setBackgroundResource(R.drawable.white_with_orange);
        allButton.setBackgroundResource(R.drawable.white_with_greenlines);

    }
    public void ClickMan(View view){
        manButton.setBackgroundResource(R.drawable.man_background);
        womanButton.setBackgroundResource(R.drawable.white_with_blacklines);
        allButton.setBackgroundResource(R.drawable.white_with_greenlines);

    }
    public void ClickAll(View view){
        womanButton.setBackgroundResource(R.drawable.white_with_blacklines);
        manButton.setBackgroundResource(R.drawable.white_with_orange);
        allButton.setBackgroundResource(R.drawable.all_background);

    }


}