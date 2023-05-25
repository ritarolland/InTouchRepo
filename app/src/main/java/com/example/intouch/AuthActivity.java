package com.example.intouch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.intouch.databinding.ActivityAuthBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthActivity extends AppCompatActivity {
    ActivityAuthBinding activityAuthBinding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAuthBinding = ActivityAuthBinding.inflate(getLayoutInflater());
        View view = activityAuthBinding.getRoot();
        setContentView(view);
    }

    public void OnClick2(View view){

        Intent intent = new Intent(AuthActivity.this, RegisterActivity.class);
        startActivity(intent);

    }
}
////        init();
//    }
//}

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
////        if(currentUser != null) {
////
////        }
//    }
//
//    public void onClickSignUpNow(View view) {
//        startActivity(new Intent(this, RegisterActivity.class));
//    }
//
//    private void init() {
//        mAuth = FirebaseAuth.getInstance();
//    }
//
////    public void onClickSignIn() {
////
////    }
//
//}