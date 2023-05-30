package com.example.intouch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        moveToNewActivity();

        return inflater.inflate(R.layout.fragment_add2, container, false);
    }

//    private void moveToNewActivity () {
//
//        Intent i = new Intent(getActivity(), AddEventActivity.class);
//        startActivity(i);
//        ((Activity) getActivity()).overridePendingTransition(0, 0);
//
//    }

}




















//


//        binding.OnlyWoMemButtonImageAttention.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
////                binding.OnlyWoMemButtonImageAttention.setBackgroundColor(Color.RED);
//            }
//        });