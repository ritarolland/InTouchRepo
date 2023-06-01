package com.example.intouch.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.intouch.adapters.EventsAdapter;
import com.example.intouch.databinding.ActivityEventsBinding;
import com.example.intouch.listeners.EventTouchListener;
import com.example.intouch.models.Event;
import com.example.intouch.utils.Constants;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class EventsActivity extends AppCompatActivity implements EventTouchListener {
    ActivityEventsBinding activityEventsBinding;
    FirebaseDatabase inTouchDatabase;
    List<Event> events;
    EventsAdapter eventsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEventsBinding = ActivityEventsBinding.inflate(getLayoutInflater());
        setContentView(activityEventsBinding.getRoot());
        init();
        listenEvents();
    }

    private void init() {
        inTouchDatabase = FirebaseDatabase.getInstance();
        events = new ArrayList<>();
        eventsAdapter = new EventsAdapter(events, this);
        activityEventsBinding.eventsRecycler.setAdapter(eventsAdapter);
    }

    private void listenEvents() {
        inTouchDatabase.getReference(Constants.KEY_COLLECTION_EVENTS).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Event event = snapshot.getValue(Event.class);
                events.add(event);
                eventsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

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

    @Override
    public void onEventClick(Event event) {
        Intent intent = new Intent(getApplicationContext(), OneEventActivity.class);
        intent.putExtra(Constants.KEY_EVENT, event);
        startActivity(intent);
        finish();
    }
}