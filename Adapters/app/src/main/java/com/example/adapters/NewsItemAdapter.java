package com.example.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsItemAdapter extends RecyclerView.Adapter<NewsHolder>{

    public static final String KEY = "KEY";
    private OnDeleteClickListener deleteListener;


    Context context;
    List<NewsItem> news;



    public interface OnDeleteClickListener{
        void onDeleteItemClick(int position);
    }
    public void SetOnDeleteClickListener(OnDeleteClickListener listener){
        deleteListener = listener;
    }

    public NewsItemAdapter(Context context, List<NewsItem> categorys) {
        this.context = context;
        this.news = categorys;
    }


    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsHolder(LayoutInflater.from(context).inflate(R.layout.news_item, parent, false), deleteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        NewsItem item = news.get(position);
        holder.headerNews.setText(news.get(position).getHeader());
        holder.imageviewNews.setImageResource(news.get(position).getImage());
        holder.deleteImage.setImageResource(R.drawable.baseline_delete_24);

    }

    @Override
    public int getItemCount() {
        return news.size();
    }

}
