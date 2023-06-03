package com.example.intouch.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.intouch.TheMostMainActivity;
import com.example.intouch.adapters.MyEventsAdapter;
import com.example.intouch.databinding.ActivityMyEventsBinding;
import com.example.intouch.listeners.EventTouchListener;
import com.example.intouch.models.Event;
import com.example.intouch.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyEventsActivity extends AppCompatActivity implements EventTouchListener {

    ActivityMyEventsBinding activityMyEventsBinding;

    FirebaseDatabase inTouchDatabase;
    FirebaseAuth mAuth;
    List<Event> myEvents;
    String currentUserId;
    DatabaseReference databaseReference;

    MyEventsAdapter myEventsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMyEventsBinding = ActivityMyEventsBinding.inflate(getLayoutInflater());
        setContentView(activityMyEventsBinding.getRoot());
        init();
        setListeners();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.child(Constants.KEY_CREATOR_ID).getValue(String.class).equals(currentUserId)) {
                    Event event = snapshot.getValue(Event.class);
                    myEvents.add(event);
                    myEventsAdapter.notifyDataSetChanged();
                    activityMyEventsBinding.myEventsRecyclerView.smoothScrollToPosition(0);
                }
                for(DataSnapshot childSnapshot : snapshot.child(Constants.KEY_MEMBERS).getChildren()) {
                    if(childSnapshot.getValue(String.class).equals(currentUserId)) {
                        Event event = snapshot.getValue(Event.class);
                        myEvents.add(event);
                        myEventsAdapter.notifyDataSetChanged();
                        activityMyEventsBinding.myEventsRecyclerView.smoothScrollToPosition(0);
                    }
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.child(Constants.KEY_CREATOR_ID).getValue(String.class).equals(currentUserId)) {
                    Event event = snapshot.getValue(Event.class);
                    myEvents.add(event);
                    myEventsAdapter.notifyDataSetChanged();
                    activityMyEventsBinding.myEventsRecyclerView.smoothScrollToPosition(0);
                }
                for(DataSnapshot childSnapshot : snapshot.child(Constants.KEY_MEMBERS).getChildren()) {
                    if(childSnapshot.getValue(String.class).equals(currentUserId)) {
                        Event event = snapshot.getValue(Event.class);
                        myEvents.add(event);
                        myEventsAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void init() {
        inTouchDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        databaseReference = inTouchDatabase.getReference(Constants.KEY_COLLECTION_EVENTS);
        myEvents = new ArrayList<>();
        currentUserId = mAuth.getCurrentUser().getUid();
        myEventsAdapter = new MyEventsAdapter(myEvents, currentUserId, this);
        activityMyEventsBinding.myEventsRecyclerView.setAdapter(myEventsAdapter);

    }

    private void setListeners() {
        activityMyEventsBinding.buttonBack.setOnClickListener(v -> onClickBack());
    }

    private void onClickBack() {
        Intent intent = new Intent(getApplicationContext(), TheMostMainActivity.class);
        intent.putExtra("FRAGMENT_TAG", "Profile");
        startActivity(intent);
    }

    @Override
    public void onEventClick(Event event) {
        Intent intent = new Intent(getApplicationContext(), OneEventActivity.class);
        intent.putExtra(Constants.KEY_EVENT, event);
        intent.putExtra("PreviousEventCategory", "myEvents");
        startActivity(intent);
        finish();
    }
}