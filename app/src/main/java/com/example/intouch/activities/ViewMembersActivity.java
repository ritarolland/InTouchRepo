package com.example.intouch.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;

import com.example.intouch.adapters.ChatCreateAdapter;
import com.example.intouch.databinding.ActivityViewMembersBinding;
import com.example.intouch.listeners.UserListener;
import com.example.intouch.models.Event;
import com.example.intouch.models.User;
import com.example.intouch.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ViewMembersActivity extends AppCompatActivity implements UserListener {
    ActivityViewMembersBinding activityViewMembersBinding;
    Event currentEvent;
    String previousEventCategory;
    FirebaseDatabase inTouchDataBase;
    private FirebaseAuth mAuth;
    List<User> users;
    ChatCreateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityViewMembersBinding = ActivityViewMembersBinding.inflate(getLayoutInflater());
        setContentView(activityViewMembersBinding.getRoot());
        init();
        setListeners();
        setInitialData();
    }

    private void init() {
        inTouchDataBase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentEvent = (Event) getIntent().getSerializableExtra(Constants.KEY_EVENT);
        previousEventCategory = getIntent().getStringExtra("PreviousEventCategory");
        users = new ArrayList<>();
        adapter = new ChatCreateAdapter(users, this);
        activityViewMembersBinding.usersRecyclerview.setAdapter(adapter);
    }

    private Bitmap getProfileImage(String encodedImage) {
        byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    private boolean idIsFound(String id) {
        for(String memberId : currentEvent.members) {
            if(memberId.equals(id)) return true;
        }
        return false;
    }

    private void setInitialData() {

        inTouchDataBase.getReference(Constants.KEY_COLLECTION_USERS).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                User user = task.getResult().child(currentEvent.creatorId).getValue(User.class);
                activityViewMembersBinding.creatorName.setText(user.userName);
                activityViewMembersBinding.creatorAvatar.setImageBitmap(getProfileImage(user.profileImage));
            }
        });

        inTouchDataBase.getReference(Constants.KEY_COLLECTION_USERS).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful() && task.getResult() != null) {
                    DataSnapshot dataSnapshot = task.getResult();
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        String id = childSnapshot.getKey();
                        if(idIsFound(id)) {
                            String email = childSnapshot.child(Constants.KEY_USER_EMAIL).getValue().toString();

                            String password = childSnapshot.child(Constants.KEY_USER_PASSWORD).getValue().toString();
                            String name = childSnapshot.child(Constants.KEY_USER_NAME).getValue().toString();

                            User newUser = new User(id, email, password, name);
                            newUser.profileImage = childSnapshot.child(Constants.KEY_PROFILE_IMAGE).getValue(String.class);
                            users.add(newUser);
                        }


                    }


                }

                adapter.notifyDataSetChanged();

            }
        });


    }

    private void setListeners() {
        activityViewMembersBinding.buttonBack.setOnClickListener(v -> onClickBack());
        activityViewMembersBinding.creatorLayout.setOnClickListener(v -> onCreatorClick());
    }

    private void onClickBack() {
        Intent intent = new Intent(getApplicationContext(), OneEventActivity.class);
        intent.putExtra(Constants.KEY_EVENT, currentEvent);
        intent.putExtra("PreviousEventCategory", previousEventCategory);
        startActivity(intent);
    }

    private void onCreatorClick() {
        if(mAuth.getCurrentUser().getUid().equals(currentEvent.creatorId)) return;
        inTouchDataBase.getReference(Constants.KEY_COLLECTION_USERS).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                User user = task.getResult().child(currentEvent.creatorId).getValue(User.class);
                onUserClick(user);
            }
        });
    }

    @Override
    public void onUserClick(User user) {
        if(mAuth.getCurrentUser().getUid().equals(user.id)) return;
        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
        intent.putExtra(Constants.KEY_USER, user);
        startActivity(intent);
        finish();
    }
}