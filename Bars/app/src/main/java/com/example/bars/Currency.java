package com.example.bars;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bars.databinding.FragmentCurrencyBinding;

import java.util.ArrayList;
import java.util.List;

public class Currency extends Fragment {

    FragmentCurrencyBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCurrencyBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NewsClass firstCurrency = new NewsClass(R.drawable.jen, "Японская иена обвалилась до нового минимума с 1990 года",
                "11:00 AM");
        NewsClass secondCurrency = new NewsClass(R.drawable.dollar, "Курс доллара упал ниже ₽92 впервые с 3 апреля",
                "12:00 PM");
        NewsClass thirdCurrency = new NewsClass(R.drawable.euro, "Курс евро впервые за три недели упал ниже ₽99",
                "13:00 PM");

        List<NewsClass> currencyNews = new ArrayList<>();
        currencyNews.add(firstCurrency);
        currencyNews.add(secondCurrency);
        currencyNews.add(thirdCurrency);

        RecyclerView recyclerView = binding.currencyRecycler;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        NewsAdapter currencyAdapter = new NewsAdapter(getContext(), currencyNews);
        recyclerView.setAdapter(currencyAdapter);
        currencyAdapter.notifyDataSetChanged();



    }
}