package com.example.intouch;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.intouch.databinding.ActivityMainBinding;


public class AddFragment extends Fragment {

    String[] item = {"Комфорт и уют", "Спортивный отдых", "Вечеринки", "Культура и развитие", "Другое"};

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems;


    ActivityMainBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
//
//        autoCompleteTextView = (AutoCompleteTextView) getView().findViewById(R.id.auto_complete_text);
//
//        adapterItems = new ArrayAdapter<String>(getActivity(), R.layout.list_item, item);
//
//        autoCompleteTextView.setAdapter(adapterItems);
//
//        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getActivity(), "item" + item, Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
        return inflater.inflate(R.layout.fragment_add2, container, false);


    }
}