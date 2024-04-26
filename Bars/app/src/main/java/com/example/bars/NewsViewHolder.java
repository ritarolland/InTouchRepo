package com.example.bars;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    ImageView image;
    TextView header;
    TextView time;
    Context context;

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        header = itemView.findViewById(R.id.header_news_item);
        time = itemView.findViewById(R.id.time_news_item);
        image = itemView.findViewById(R.id.image_news_item);

    }
}
