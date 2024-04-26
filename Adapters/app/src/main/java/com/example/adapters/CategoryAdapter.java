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

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder>{


    public interface OnCategoryClickListener{
        void onCategoryClick(int position, CategoryItem category);
    }
    private OnCategoryClickListener onClickListener;

    public static final String KEY = "KEY";

    Context context;
    List<CategoryItem> categorys;

    public CategoryAdapter(Context context, List<CategoryItem> categorys, OnCategoryClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.context = context;
        this.categorys = categorys;
    }

    public CategoryAdapter(Context context, List<CategoryItem> categorys){
        this.context = context;
        this.categorys = categorys;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryItem item = categorys.get(position);
        holder.header.setText(categorys.get(position).getHeader());
        holder.imgview.setImageResource(categorys.get(position).getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onCategoryClick(position, item);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return categorys.size();
    }


}
