package com.example.intouch;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.intouch.databinding.FragmentAdd2Binding;



public class AddFragment extends Fragment {
    FragmentAdd2Binding binding;
    AppCompatButton WomanButton, ManButton, AllButton;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAdd2Binding.inflate(inflater, container, false);
        View view = binding.getRoot();


        WomanButton = (AppCompatButton) binding.WomanButton;
        ManButton = (AppCompatButton) binding.ManButton;
        AllButton =(AppCompatButton) binding.AllButton;



        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (getView().getId()){
                    case R.id.WomanButton:
                        WomanButton.setBackgroundColor(0);
                        break;
                    case R.id.ManButton:
                        ManButton.setBackgroundColor(Color.RED);
                        break;
                    case R.id.AllButton:
                        AllButton.setBackgroundColor(Color.CYAN);
                        break;

                }

            }
        };

        WomanButton.setOnClickListener(onClickListener);
        ManButton.setOnClickListener(onClickListener);
        AllButton.setOnClickListener(onClickListener);


        return inflater.inflate(R.layout.fragment_add2, container, false);
    }

}




















//


//        binding.WomanButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
////                binding.WomanButton.setBackgroundColor(Color.RED);
//            }
//        });