package com.example.intouch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intouch.adapters.MessengerAdapter;
import com.example.intouch.databinding.ActivityMessengerBinding;
import com.example.intouch.models.User;
import com.example.intouch.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MessengerActivity extends AppCompatActivity {
    ActivityMessengerBinding activityMessengerBinding;

    FirebaseDatabase inTouchDataBase = FirebaseDatabase.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMessengerBinding = ActivityMessengerBinding.inflate(getLayoutInflater());
        View view = activityMessengerBinding.getRoot();
        setContentView(view);
        setInitialData();

    }

    private void setInitialData() {

        inTouchDataBase.getReference(Constants.KEY_COLLECTION_USERS).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                ArrayList<User> chats = new ArrayList<>();

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

                        chats.add(new User(id, email, password, name));

                    }


                }
                RecyclerView recyclerView = activityMessengerBinding.chats;
                MessengerAdapter messengerAdapter = new MessengerAdapter(getLayoutInflater(), chats);
                recyclerView.setAdapter(messengerAdapter);





            }
        });

    }


//    private void setInitialData(){
//        for(int i = 0; i < 5; i++) {
//            chats.add(new User ("id", "email", "pass", "name"));
//            chats.add(new User ("id", "email", "pass", "name"));
//            chats.add(new User ("id", "email", "pass", "name"));
//            chats.add(new User ("id", "email", "pass", "name"));
//            chats.add(new User ("id", "email", "pass", "name"));
//        }
//
//
//    }*/

    public void onClickNewChat(View view) {
        startActivity(new Intent(this, ChatCreateActivity.class));
    }
}