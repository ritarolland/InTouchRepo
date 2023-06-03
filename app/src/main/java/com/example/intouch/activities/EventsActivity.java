package com.example.intouch.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.intouch.TheMostMainActivity;
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
import java.util.Objects;

public class EventsActivity extends AppCompatActivity implements EventTouchListener {
    ActivityEventsBinding activityEventsBinding;
    FirebaseDatabase inTouchDatabase;
    List<Event> events;
    EventsAdapter eventsAdapter;
    String eventCategory = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEventsBinding = ActivityEventsBinding.inflate(getLayoutInflater());
        setContentView(activityEventsBinding.getRoot());
        init();
        setListeners();
        listenEvents();
    }

    private void setListeners() {
        activityEventsBinding.buttonBack.setOnClickListener(v -> onClickBack());
    }

    private void onClickBack() {
        startActivity(new Intent(getApplicationContext(), TheMostMainActivity.class));
    }

    private void init() {
        inTouchDatabase = FirebaseDatabase.getInstance();
        events = new ArrayList<>();
        eventsAdapter = new EventsAdapter(events, this);
        activityEventsBinding.eventsRecycler.setAdapter(eventsAdapter);
        eventCategory = getIntent().getStringExtra(Constants.KEY_SELECTED_CATEGORY);
    }


    private void listenEvents() {
        inTouchDatabase.getReference(Constants.KEY_COLLECTION_EVENTS).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Event event = snapshot.getValue(Event.class);
                if(Objects.equals(eventCategory, "Все") || eventCategory == null) {
                    events.add(event);
                } else if(event.eventCategory.equals(eventCategory)) {
                    events.add(event);
                }
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
        intent.putExtra("PreviousEventCategory", eventCategory);
        startActivity(intent);
        finish();
    }
}