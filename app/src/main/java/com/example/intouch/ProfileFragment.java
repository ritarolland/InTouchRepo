package com.example.intouch;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.intouch.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentProfileBinding.inflate(inflater, container, false);


        binding.textViewemail.setText(Html.fromHtml("<a href = \"mailto:megatroxx@mail.ru\"> Send Feedback </a>"));
        binding.textViewemail.setMovementMethod(LinkMovementMethod.getInstance());

        return binding.getRoot();
    }
}