package com.example.intouch.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.intouch.TheMostMainActivity;
import com.example.intouch.databinding.ActivityRegisterBinding;
import com.example.intouch.models.User;
import com.example.intouch.utils.Constants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding activityRegisterBinding;
    private FirebaseDatabase inTouchDataBase;
    private FirebaseAuth mAuth;
    private DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = activityRegisterBinding.getRoot();
        setContentView(view);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            startActivity(new Intent(this, TheMostMainActivity.class));
        }
    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
        inTouchDataBase = FirebaseDatabase.getInstance();
        users = inTouchDataBase.getReference(Constants.KEY_COLLECTION_USERS);
    }

    public void onClickSignInNow(View view) {
        startActivity(new Intent(this, AuthActivity.class));
    }

    public void onClickSignUp(View view) {
        String email, password, name;
        email = activityRegisterBinding.emailEdittext.getText().toString();
        password = activityRegisterBinding.passwordEdittext.getText().toString();
        name = activityRegisterBinding.nameEdittext.getText().toString();
        if(!TextUtils.isEmpty(email)
                && !TextUtils.isEmpty(password)
                && !TextUtils.isEmpty(name)) {

            mAuth.createUserWithEmailAndPassword(activityRegisterBinding.emailEdittext.getText().toString(),
                            activityRegisterBinding.passwordEdittext.getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            if(currentUser != null) {
                                User newUser = new User(currentUser.getUid(), email, password, name);
                                users.child(currentUser.getUid()).setValue(newUser);
                                startActivity(new Intent(getApplicationContext(), TheMostMainActivity.class));
                            }
                            Toast.makeText(getApplicationContext(), "Signed up successfully", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Sign up failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }
}