package com.example.intouch.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.intouch.adapters.ChatCreateAdapter;
import com.example.intouch.databinding.ActivityChatCreateBinding;
import com.example.intouch.listeners.UserListener;
import com.example.intouch.models.User;
import com.example.intouch.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatCreateActivity extends AppCompatActivity implements UserListener {
    ActivityChatCreateBinding activityChatCreateBinding;
    FirebaseDatabase inTouchDataBase = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChatCreateBinding = ActivityChatCreateBinding.inflate(getLayoutInflater());
        setContentView(activityChatCreateBinding.getRoot());
        setListeners();
        setInitialData();

    }

    private void setListeners() {
        activityChatCreateBinding.buttonBack.setOnClickListener(v -> goBack());
    }


    private void setInitialData() {

        inTouchDataBase.getReference(Constants.KEY_COLLECTION_USERS).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                ArrayList<User> users = new ArrayList<>();

                if (task.isSuccessful() && task.getResult() != null) {
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    String currentUserId = currentUser.getUid();

                    DataSnapshot dataSnapshot = task.getResult();

                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        if (currentUserId.equals(childSnapshot.getKey())) {
                            continue;
                        }
                        String email = childSnapshot.child(Constants.KEY_USER_EMAIL).getValue().toString();

                        String password = childSnapshot.child(Constants.KEY_USER_PASSWORD).getValue().toString();
                        String name = childSnapshot.child(Constants.KEY_USER_NAME).getValue().toString();
                        String id = childSnapshot.getKey();
                        User newUser = new User(id, email, password, name);
                        newUser.profileImage = childSnapshot.child(Constants.KEY_PROFILE_IMAGE).getValue(String.class);
                        users.add(newUser);

                    }


                }
                RecyclerView recyclerView = activityChatCreateBinding.users;
                ChatCreateAdapter chatCreateAdapter = new ChatCreateAdapter(users, ChatCreateActivity.this);

                recyclerView.setAdapter(chatCreateAdapter);


            }
        });

    }


    @Override
    public void onUserClick(User user) {
        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
        intent.putExtra(Constants.KEY_USER, user);
        startActivity(intent);
        finish();
    }

    private void goBack() {
        Intent intent = new Intent(this, TheMostMainActivity.class);
        intent.putExtra(Constants.KEY_FRAGMENT_TAG, "Messenger");
        startActivity(intent);
    }
}