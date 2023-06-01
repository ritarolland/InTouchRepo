package com.example.intouch.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.intouch.databinding.ActivityOneEventBinding;
import com.example.intouch.models.Event;
import com.example.intouch.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class OneEventActivity extends AppCompatActivity {
    ActivityOneEventBinding activityOneEventBinding;
    Event currentEvent;
    FirebaseDatabase inTouchDatabase;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOneEventBinding = ActivityOneEventBinding.inflate(getLayoutInflater());
        setContentView(activityOneEventBinding.getRoot());
        init();
        setListeners();
        setInitialData();

    }

    private void init() {
        inTouchDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    private void setListeners() {
        activityOneEventBinding.respondButton.setOnClickListener(v -> respond());
    }

    private void setInitialData() {
        currentEvent = (Event) getIntent().getSerializableExtra(Constants.KEY_EVENT);
        activityOneEventBinding.eventName.setText(currentEvent.eventName);
        activityOneEventBinding.eventDescription.setText(currentEvent.eventDescription);
    }

    private void respond() {
        String currentUserId = mAuth.getCurrentUser().getUid();
        currentEvent.members.add(currentUserId);
        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT);
        inTouchDatabase.getReference(Constants.KEY_COLLECTION_EVENTS).child(currentEvent.eventId).setValue(currentEvent);

    }
}