package com.example.adapters;

import static com.example.adapters.CategoryAdapter.KEY;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends RecyclerView.ViewHolder{

    ImageView imgview;
    TextView header;
    Context context;


    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        imgview = itemView.findViewById(R.id.imageview);
        header = itemView.findViewById(R.id.header);
        itemView.setClickable(true);

    }
}
