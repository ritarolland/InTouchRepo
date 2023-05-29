package com.example.intouch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.intouch.databinding.ActivityAddEventBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.Calendar;
import java.util.Locale;

public class AddEventActivity extends AppCompatActivity {

    AppCompatButton womanButton;
    AppCompatButton manButton;
    AppCompatButton allButton;
    AppCompatButton SelectDate;

    private int jam, menit;

    TextView text;

    Button SelectTime;

    TextView Text2;
    String[] item = {"Комфорт", "Спорт", "Вечеринки", "Саморазвитие", "Другое"};

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems;

    ActivityAddEventBinding binding;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        binding = ActivityAddEventBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        autoCompleteTextView = findViewById(R.id.auto_complete_text);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, item);

        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Item = parent.getItemAtPosition(position).toString();

            }
        });


//        SelectDate = findViewById(R.id.SelectDate);
//        SelectDate.setBackgroundResource(R.drawable.man_background);
        text = findViewById(R.id.textView);
        SelectTime = findViewById(R.id.button);
        Text2 = findViewById(R.id.textView2);

        MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Выберите дату").setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build();


        binding.SelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show(getSupportFragmentManager(), "Material Get Picker");
                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        text.setText(datePicker.getHeaderText());
                    }
                });

            }
        });
        SelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                jam = calendar.get(Calendar.HOUR_OF_DAY);
                menit = calendar.get(Calendar.MINUTE);

                TimePickerDialog dialog;
                dialog = new TimePickerDialog(AddEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        jam = hourOfDay;
                        menit = minute;

                        if (jam <= 12){
                            Text2.setText(String.format(Locale.getDefault(), "%d:%d AM", jam, menit));
                        }
                        else{
                            Text2.setText(String.format(Locale.getDefault(), "%d:%d PM", jam, menit));
                        }

                    }
                }, jam, menit, true);
                dialog.show();

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