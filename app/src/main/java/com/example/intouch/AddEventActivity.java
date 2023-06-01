package com.example.intouch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.intouch.databinding.ActivityAddEventBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.slider.RangeSlider;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddEventActivity extends AppCompatActivity {



    AppCompatButton WomanButton;
    AppCompatButton OnlyMenButtonAttention;
    AppCompatButton AllButtonAttention;
    AppCompatButton SelectDate;

    int counter = 1;
    EditText ToPeopleNumberAttention;

    SwitchCompat Switch;

    private RangeSlider stepSlider;

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

        stepSlider = findViewById(R.id.RangeSlider);  //here
        Switch = (SwitchCompat) findViewById(R.id.Switch);
        ToPeopleNumberAttention = (EditText) findViewById(R.id.ToPeopleNumberAttention);


        autoCompleteTextView = findViewById(R.id.CategoryAttention);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, item);

        autoCompleteTextView.setAdapter(adapterItems);
        ClickSwitch(view);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Item = parent.getItemAtPosition(position).toString();

            }
        });


//        SelectDate = findViewById(R.id.SelectDate);
//        SelectDate.setBackgroundResource(R.drawable.man_background);
        text = findViewById(R.id.justlinetext);
        SelectTime = findViewById(R.id.SelectTime);
        Text2 = findViewById(R.id.justlinetext2);

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


                RangeSlider rangeSlider = findViewById(R.id.RangeSlider);
                TextView textViewStart = findViewById(R.id.textViewStart);

                rangeSlider.addOnSliderTouchListener(new RangeSlider.OnSliderTouchListener() {
                    @Override
                    public void onStartTrackingTouch(RangeSlider slider) {
                        List<Float> values = slider.getValues();
                    }

                    @Override
                    public void onStopTrackingTouch(RangeSlider slider) {
                        List<Float> values = slider.getValues();
                        textViewStart.setText((Math.round(values.get(0))) + " " + Math.round(values.get(1)));
                    }

                    public void onStopTracking(RangeSlider slider) {
                        List<Float> values = slider.getValues();
                        textViewStart.setText((Math.round(values.get(0))) + " " + Math.round(values.get(1)));
                    }
                });






        WomanButton = (AppCompatButton) findViewById(R.id.WomanButton);
        OnlyMenButtonAttention = (AppCompatButton) findViewById(R.id.OnlyMenButtonAttention);
        AllButtonAttention = (AppCompatButton) findViewById(R.id.AllButtonAttention);


    }
    public void ClickWoman(View view){
        WomanButton.setBackgroundResource(R.drawable.woman_backgroud);
        OnlyMenButtonAttention.setBackgroundResource(R.drawable.white_with_orange);
        AllButtonAttention.setBackgroundResource(R.drawable.white_with_greenlines);

    }
    public void ClickMan(View view){
        OnlyMenButtonAttention.setBackgroundResource(R.drawable.man_background);
        WomanButton.setBackgroundResource(R.drawable.white_with_blacklines);
        AllButtonAttention.setBackgroundResource(R.drawable.white_with_greenlines);

    }
    public void ClickAll(View view){
        WomanButton.setBackgroundResource(R.drawable.white_with_blacklines);
        OnlyMenButtonAttention.setBackgroundResource(R.drawable.white_with_orange);
        AllButtonAttention.setBackgroundResource(R.drawable.all_background);

    }

    public void ClickSwitch(View view){

        Switch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (counter % 2 == 1) {
                    counter += 1;
                    ToPeopleNumberAttention.setText("Любое");
                    ToPeopleNumberAttention.setInputType(0);
                }
                else {
                    counter += 1;
                    ToPeopleNumberAttention.setInputType(InputType.TYPE_CLASS_NUMBER);
                    ToPeopleNumberAttention.setText("");
                    ToPeopleNumberAttention.setHint("Введите число");
                }
            }

        });
    }


}