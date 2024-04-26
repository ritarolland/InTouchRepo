package com.example.bars;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    Context context;
    List<NewsClass> news;

    public NewsAdapter(Context context, List<NewsClass> news){
        this.news = news;
        this.context = context;
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.image.setImageResource(news.get(position).getImage());
        holder.header.setText(news.get(position).getHeader());
        holder.time.setText(news.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return news.size();
    }
}
