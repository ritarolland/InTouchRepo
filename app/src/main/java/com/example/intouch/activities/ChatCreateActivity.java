package com.example.intouch.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.intouch.adapters.ChatCreateAdapter;
import com.example.intouch.adapters.MessengerAdapter;
import com.example.intouch.databinding.ActivityChatCreateBinding;
import com.example.intouch.models.User;
import com.example.intouch.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChatCreateActivity extends AppCompatActivity {
    ActivityChatCreateBinding activityChatCreateBinding;
    List<User> users;
    FirebaseDatabase inTouchDataBase = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChatCreateBinding = ActivityChatCreateBinding.inflate(getLayoutInflater());
        setContentView(activityChatCreateBinding.getRoot());
        setInitialData();

        /*if(!users.isEmpty()) {
            ChatCreateAdapter chatCreateAdapter = new ChatCreateAdapter(this.getLayoutInflater(), users);
            activityChatCreateBinding.users.setAdapter(chatCreateAdapter);
        }*/
    }


    private void setInitialData() {
        inTouchDataBase.getReference(Constants.KEY_COLLECTION_USERS).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if(task.isSuccessful() && task.getResult() != null) {

                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    String currentUserId = currentUser.getUid().toString();
                    users = new ArrayList<>();
                    DataSnapshot dataSnapshot = task.getResult();
                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                    for(DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        /*String id = childSnapshot.
                        *//*if(currentUserId.equals(id)) {
                            continue;
                        }
                        *//*String email = childSnapshot.child(Constants.KEY_USER_EMAIL).getValue().toString();
//                        String password = childSnapshot.child(Constants.KEY_USER_PASSWORD).getValue().toString();
//                        String name = childSnapshot.child(Constants.KEY_USER_NAME).getValue().toString();
//                        User user = new User(id, email, password, name);
//                        users.add(user);*/
                    }

                }
            }
        });
    }
}