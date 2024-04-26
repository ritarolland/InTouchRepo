package com.example.adapters;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsHolder extends RecyclerView.ViewHolder{

    ImageView imageviewNews;
    TextView headerNews;
    Context context;
    ImageView deleteImage;
    Button addImage;

    public NewsHolder(@NonNull View itemView, NewsItemAdapter.OnDeleteClickListener deleteListener) {
        super(itemView);
        context = itemView.getContext();
        imageviewNews = itemView.findViewById(R.id.imageviewNews);
        headerNews = itemView.findViewById(R.id.headerNews);
        deleteImage = itemView.findViewById(R.id.deleteimage);
        addImage = itemView.findViewById(R.id.addButton);

        deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteListener.onDeleteItemClick(getAdapterPosition());
            }
        });

    }
}