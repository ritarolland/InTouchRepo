package com.example.firsttask;
import static com.example.firsttask.MainActivity.NAME;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.firsttask.databinding.ActivityMainBinding;
import com.example.firsttask.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    ActivitySecondBinding binding;

    public static final String SUBJECT = "SUBJECT";
    public static final String INFO = "INFO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        GetIntent();

        binding.SecondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToThirdActivity(view);
            }
        });


    }
    protected void GetIntent() {
        Bundle args = getIntent().getExtras();
        if (args != null) {
            String[] names = (String[]) args.get(NAME);
            binding.NameSecondActivity.setText(names[0]);
            binding.SurnameSecondActivity.setText(names[1]);
        }
    }


    protected void ToThirdActivity(View view){
        Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
        String subject = binding.SubjectName.getText().toString();
        intent.putExtra(SUBJECT, subject);
        Launcher.launch(intent);

    }

    ActivityResultLauncher<Intent> Launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK){
                Intent intent = result.getData();
                String Access = intent.getStringExtra(INFO);
                binding.TimeResult.setText(Access);
                Bundle args = intent.getExtras();
                String[] info = args.getStringArray(INFO);
                binding.ResultDay.setText(info[0]);
                binding.TimeResult.setText(info[1]);
                Toast.makeText(getApplicationContext(), "Данные успешно сохранены", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Что-то пошло не так", Toast.LENGTH_LONG).show();
            }

        }
    });
}