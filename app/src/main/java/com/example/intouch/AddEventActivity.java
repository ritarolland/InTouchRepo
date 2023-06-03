package com.example.intouch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.intouch.activities.EventsActivity;
import com.example.intouch.databinding.ActivityAddEventBinding;
import com.example.intouch.models.Event;
import com.example.intouch.utils.Constants;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.slider.RangeSlider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddEventActivity extends AppCompatActivity {
    ActivityAddEventBinding binding;
    FirebaseDatabase inTouchDatabase;
    FirebaseAuth mAuth;
    Event newEvent;
    ArrayAdapter<String> adapterItems;
    int counter = 1;
    private int jam, menit;
    String[] item = {"Комфорт", "Спорт", "Вечеринки", "Саморазвитие", "Другое"};
    MaterialDatePicker<Long> datePicker;
    List<Float> ageValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        setListeners();
    }

    private void init() {
        inTouchDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        newEvent = new Event();
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, item);
        binding.category.setAdapter(adapterItems);
        datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Выберите дату")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build();
        ageValues = new ArrayList<>();
    }

    private void onClickBack() {
        startActivity(new Intent(getApplicationContext(), TheMostMainActivity.class));
    }

    private void setListeners() {
        binding.buttonBack.setOnClickListener(v -> onClickBack());
        binding.buttonCreateEvent.setOnClickListener(v -> createEvent());
        binding.Switch.setOnClickListener(v -> clickSwitch());
        binding.timeSelectButton.setOnClickListener(v -> selectTime());
        binding.SelectDate.setOnClickListener(v -> {
            datePicker.show(getSupportFragmentManager(), "Material Get Picker");
            datePicker.addOnPositiveButtonClickListener(selection -> binding.dateTextview.setText(datePicker.getHeaderText()));

        });
        binding.RangeSlider.addOnSliderTouchListener(new RangeSlider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull RangeSlider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull RangeSlider slider) {
                ageValues = slider.getValues();
            }
        });

    }

    public void selectTime() {
        Calendar calendar = Calendar.getInstance();
        jam = calendar.get(Calendar.HOUR_OF_DAY);
        menit = calendar.get(Calendar.MINUTE);

        TimePickerDialog dialog;
        dialog = new TimePickerDialog(AddEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view1, int hourOfDay, int minute) {
                jam = hourOfDay;
                menit = minute;

                if (jam <= 12){
                    binding.timeTextview.setText(String.format(Locale.getDefault(), "%d:%d AM", jam, menit));
                }
                else{
                    binding.timeTextview.setText(String.format(Locale.getDefault(), "%d:%d PM", jam, menit));
                }

            }
        }, jam, menit, true);
        dialog.show();
    }

    public void clickSwitch(){
        if (counter % 2 == 1) {
            counter += 1;

            binding.maxMemberNumber.setText("Любое");
            binding.maxMemberNumber.setInputType(0);
        }
        else {
            counter += 1;
            binding.maxMemberNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
            binding.maxMemberNumber.setText("");
            binding.maxMemberNumber.setHint("Введите число");
        }

    }

    private void createEvent() {
        if(binding.eventName.getText().toString().isEmpty()
        || binding.timeTextview.getText().toString().isEmpty()
        || binding.dateTextview.getText().toString().isEmpty()
        || binding.category.getText().toString().isEmpty()
        || binding.eventDescription.getText().toString().isEmpty()
        || binding.minMemberNumber.getText().toString().isEmpty()
        || binding.maxMemberNumber.getText().toString().isEmpty()
        || ageValues.size() == 0) {
            Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }
        newEvent.ageMin = Math.round(ageValues.get(0));
        newEvent.ageMax = Math.round(ageValues.get(1));
        newEvent.creatorId = mAuth.getCurrentUser().getUid();
        newEvent.eventName = binding.eventName.getText().toString();
        newEvent.time = binding.timeTextview.getText().toString();
        newEvent.date = binding.dateTextview.getText().toString();
        newEvent.eventCategory = binding.category.getText().toString();
        newEvent.eventDescription = binding.eventDescription.getText().toString();
        newEvent.numbMin = Integer.parseInt(binding.minMemberNumber.getText().toString());
        if(binding.maxMemberNumber.getText().toString().equals("Любое")) {
            newEvent.numbMax = -1;
        } else newEvent.numbMax = Integer.parseInt(binding.maxMemberNumber.getText().toString());
        newEvent.dateObject = new Date();
        String eventId = inTouchDatabase.getReference(Constants.KEY_COLLECTION_EVENTS).push().getKey();
        newEvent.eventId = eventId;
        inTouchDatabase.getReference(Constants.KEY_COLLECTION_EVENTS)
                .child(eventId) .setValue(newEvent).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                Toast.makeText(getApplicationContext(), "Event successfully created", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });

        startActivity(new Intent(getApplicationContext(), EventsActivity.class));

    }

    public void ClickWoman(View view){
        binding.WomanButton.setBackgroundResource(R.drawable.woman_backgroud);
        binding.ManButton.setBackgroundResource(R.drawable.white_with_orange);
        binding.AllButton.setBackgroundResource(R.drawable.white_with_greenlines);
        newEvent.onlyGirls = true;
        newEvent.onlyBoys = false;

    }
    public void ClickMan(View view){
        binding.ManButton.setBackgroundResource(R.drawable.man_background);
        binding.WomanButton.setBackgroundResource(R.drawable.white_with_blacklines);
        binding.AllButton.setBackgroundResource(R.drawable.white_with_greenlines);
        newEvent.onlyBoys = true;
        newEvent.onlyGirls = false;

    }
    public void ClickAll(View view){
        binding.WomanButton.setBackgroundResource(R.drawable.white_with_blacklines);
        binding.ManButton.setBackgroundResource(R.drawable.white_with_orange);
        binding.AllButton.setBackgroundResource(R.drawable.all_background);
        newEvent.onlyBoys = false;
        newEvent.onlyGirls = false;

    }

}