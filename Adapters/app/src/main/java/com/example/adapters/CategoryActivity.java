package com.example.adapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.adapters.databinding.ActivityCategoryBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    ActivityCategoryBinding binding;

    public static final String KEY = "KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        String[] headers = {"Экономика", "Политика", "Спорт", "Мероприятия", "Образование", "Обсуждаемое", "Шоу-биз", "Прочее"};

        List<CategoryItem> category = new ArrayList<>();
        category.add(new CategoryItem(R.drawable.ecomonic, headers[0]));
        category.add(new CategoryItem(R.drawable.politik, headers[1]));
        category.add(new CategoryItem(R.drawable.sport, headers[2]));
        category.add(new CategoryItem(R.drawable.events, headers[3]));
        category.add(new CategoryItem(R.drawable.education, headers[4]));
        category.add(new CategoryItem(R.drawable.talking, headers[5]));
        category.add(new CategoryItem(R.drawable.show, headers[6]));
        category.add(new CategoryItem(R.drawable.other, headers[7]));

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CategoryAdapter.OnCategoryClickListener categoryClickListener = new CategoryAdapter.OnCategoryClickListener() {
            @Override
            public void onCategoryClick(int position, CategoryItem category) {
                Intent intent = new Intent(CategoryActivity.this, CategoryNewsActivity.class);
                intent.putExtra(KEY, category.getHeader());
                startActivity(intent);
            }
        };

        recyclerView.setAdapter(new CategoryAdapter(getApplicationContext(), category, categoryClickListener));

    }

}