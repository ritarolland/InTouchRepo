package com.example.intouch.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
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
    String previousEventCategory = null;
    Handler handler;


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
        previousEventCategory = getIntent().getStringExtra("PreviousEventCategory");
        handler = new Handler();
    }

    private void setListeners() {
        activityOneEventBinding.buttonBack.setOnClickListener(v -> onClickBack());
        activityOneEventBinding.checkMembers.setOnClickListener(v -> viewMembers());
    }

    private void viewMembers() {
        Intent intent = new Intent(getApplicationContext(), ViewMembersActivity.class);
        intent.putExtra(Constants.KEY_EVENT, currentEvent);
        intent.putExtra("PreviousEventCategory", previousEventCategory);
        startActivity(intent);
    }

    private void onClickBack() {
        if(previousEventCategory == null) {
            startActivity(new Intent(getApplicationContext(), TheMostMainActivity.class));
        }else if(previousEventCategory.equals("myEvents")) {
            startActivity(new Intent(getApplicationContext(), MyEventsActivity.class));
        } else {
            Intent intent = new Intent(getApplicationContext(), EventsActivity.class);
            intent.putExtra(Constants.KEY_SELECTED_CATEGORY, previousEventCategory);
            startActivity(intent);
        }
    }

    private void setInitialData() {
        currentEvent = (Event) getIntent().getSerializableExtra(Constants.KEY_EVENT);
        activityOneEventBinding.eventName.setText(currentEvent.eventName);
        activityOneEventBinding.eventDescription.setText(currentEvent.eventDescription);
        if(currentEvent.onlyGirls) {
            activityOneEventBinding.onlyGirlsTextview.setText("Только женщины");
        } else if(currentEvent.onlyBoys) {
            activityOneEventBinding.onlyGirlsTextview.setText("Только мужчины");
        } else {
            activityOneEventBinding.onlyGirlsTextview.setText("Мужчины и женщины");
        }
        String age = ((Integer)currentEvent.ageMin).toString() + " - " + ((Integer)currentEvent.ageMax).toString();
        activityOneEventBinding.AgeInfo.setText(age);
        String date = currentEvent.date + " " + currentEvent.time;
        activityOneEventBinding.TimeInfo.setText(date);
        if(currentEvent.numbMax != -1) {
            activityOneEventBinding.EndPeopleOrNotText.setText("Ограниченное количество участников");
        } else {
            activityOneEventBinding.EndPeopleOrNotText.setText("Неограниченное количество участников");
        }
        activityOneEventBinding.eventDescription.setText(currentEvent.eventDescription);
        String numberPeople;
        if(currentEvent.numbMax != -1) {
            numberPeople = ((Integer)currentEvent.members.size()).toString() + "/" + ((Integer)currentEvent.numbMax).toString();
            activityOneEventBinding.progressBar.setVisibility(View.VISIBLE);
            activityOneEventBinding.progressBar.setMax(currentEvent.numbMax);
            new Thread(() -> handler.post(() -> activityOneEventBinding.progressBar.setProgress(currentEvent.members.size()))).start();


        } else {
            numberPeople = ((Integer)currentEvent.members.size()).toString() + "/...";
        }
        activityOneEventBinding.NumberPeople.setText(numberPeople);
        activityOneEventBinding.category.setText(currentEvent.eventCategory);


        updateRespondButton();
    }

    private void refuseTheEvent() {
        String currentUserId = mAuth.getCurrentUser().getUid();
        for(int i = 0; i < currentEvent.members.size(); i++) {
            if(currentEvent.members.get(i).equals(currentUserId)) {
                currentEvent.members.remove(i);
            }
        }
        inTouchDatabase.getReference(Constants.KEY_COLLECTION_EVENTS).child(currentEvent.eventId).setValue(currentEvent);
        Toast.makeText(getApplicationContext(), "You have opted out", Toast.LENGTH_SHORT).show();
        updateRespondButton();
    }

    private void deleteTheEvent() {
        inTouchDatabase.getReference(Constants.KEY_COLLECTION_EVENTS).child(currentEvent.eventId).removeValue()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Event deleted", Toast.LENGTH_SHORT).show();
                        onClickBack();
                    }
                    else Toast.makeText(getApplicationContext(), "Failed to delete the event", Toast.LENGTH_SHORT).show();
                });
    }

    private void updateRespondButton() {
        String currentUserId = mAuth.getCurrentUser().getUid();
        if(currentEvent.creatorId.equals(currentUserId)){
            activityOneEventBinding.respondButton.setText("Удалить событие");
            activityOneEventBinding.respondButton.setOnClickListener(v -> deleteTheEvent());
            return;
        } else {
            for(String member : currentEvent.members) {
                if (member.equals(currentUserId)) {
                    activityOneEventBinding.respondButton.setText("Отказаться от участия");
                    activityOneEventBinding.respondButton.setOnClickListener(v -> refuseTheEvent());
                    return;
                }
            }
        }
        activityOneEventBinding.respondButton.setText("Откликнуться");
        activityOneEventBinding.respondButton.setOnClickListener(v -> respond());
    }

    private void respond() {
        String currentUserId = mAuth.getCurrentUser().getUid();
        /*if(currentEvent.creatorId.equals(currentUserId)){
            Toast.makeText(getApplicationContext(), "You are the event creator", Toast.LENGTH_SHORT).show();
            return;
        } else {
            for(String member : currentEvent.members) {
                if (member.equals(currentUserId)) {
                    Toast.makeText(getApplicationContext(), "You are already a member of the event", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }*/
        if(currentEvent.members.size() >= currentEvent.numbMax && currentEvent.numbMax != -1) {
            Toast.makeText(getApplicationContext(), "The max member number has been reached", Toast.LENGTH_SHORT).show();
            return;
        }
        currentEvent.members.add(currentUserId);
        inTouchDatabase.getReference(Constants.KEY_COLLECTION_EVENTS).child(currentEvent.eventId).setValue(currentEvent);
        Toast.makeText(getApplicationContext(), "You are now a member of the event", Toast.LENGTH_SHORT).show();
        updateRespondButton();
    }
}