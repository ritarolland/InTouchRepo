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

import com.example.bars.databinding.FragmentPolitikBinding;

import java.util.ArrayList;
import java.util.List;

public class Politik extends Fragment {



    FragmentPolitikBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPolitikBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        NewsClass first = new NewsClass(R.drawable.merkel, "Меркель объявила об уходе с должности канцлера",
                "9:00 AM");

        NewsClass second = new NewsClass(R.drawable.nato, "В Кремле прокомментировали секретную поставку США ракет ATACMS",
                "13:36 PM");

        NewsClass third = new NewsClass(R.drawable.oon, "Сигрид Кааг: облегчение страданий жителей Газы – наша коллективная ответственность",
                "13:36 PM");

        List<NewsClass> news = new ArrayList();
        news.add(first);
        news.add(second);
        news.add(third);

        RecyclerView recyclerView = binding.fragmentPolitikRecycler;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        NewsAdapter adapter = new NewsAdapter(getContext(), news);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();




    }
}