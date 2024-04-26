package com.example.adapters;

import static com.example.adapters.CategoryAdapter.KEY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.adapters.databinding.ActivityCategoryBinding;
import com.example.adapters.databinding.ActivityCategoryNewsBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoryNewsActivity extends AppCompatActivity {

    ActivityCategoryNewsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryNewsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String type = getIntent().getStringExtra(KEY);

        binding.NewsType.setText(type.toString());

        String[] header = {"Рубль упал", "Доллар упал", "Евро упало", "Юань упал",
                "Шекель упал", "Рупий упал", "Тенге упал", "Ливрик упал"};

        ArrayList<NewsItem> news = new ArrayList<>();
        news.add(new NewsItem(R.drawable.currency, header[0]));
        news.add(new NewsItem(R.drawable.currency, header[1]));
        news.add(new NewsItem(R.drawable.currency, header[2]));
        news.add(new NewsItem(R.drawable.currency, header[3]));
        news.add(new NewsItem(R.drawable.currency, header[4]));
        news.add(new NewsItem(R.drawable.currency, header[5]));
        news.add(new NewsItem(R.drawable.currency, header[6]));
        news.add(new NewsItem(R.drawable.currency, header[7]));



        RecyclerView recyclerView = binding.recyclerNews;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NewsItemAdapter myAdapter = new NewsItemAdapter(getApplicationContext(), news);

        recyclerView.setAdapter(myAdapter);
        myAdapter.SetOnDeleteClickListener(new NewsItemAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteItemClick(int position) {
                news.remove(position);
                myAdapter.notifyItemRemoved(position);
            }
        });

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.edittext.getText().toString().length() == 0){
                    Toast.makeText(CategoryNewsActivity.this, "Your input is empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    news.add(new NewsItem(R.drawable.currency, binding.edittext.getText().toString()));
                    myAdapter.notifyItemInserted(news.size() - 1);
                    binding.edittext.setText("");
                }

            }
        });
    }

}