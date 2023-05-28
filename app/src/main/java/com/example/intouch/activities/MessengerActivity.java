package com.example.intouch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.intouch.adapters.RecentConversationsAdapter;
import com.example.intouch.databinding.ActivityMessengerBinding;
import com.example.intouch.listeners.ConversionListener;
import com.example.intouch.models.ChatMessage;
import com.example.intouch.models.User;
import com.example.intouch.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MessengerActivity extends AppCompatActivity implements ConversionListener {

    ActivityMessengerBinding activityMessengerBinding;
    private List<ChatMessage> conversations;
    private RecentConversationsAdapter conversationsAdapter;

    FirebaseDatabase inTouchDataBase;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMessengerBinding = ActivityMessengerBinding.inflate(getLayoutInflater());
        setContentView(activityMessengerBinding.getRoot());
        init();
        listenConversations();

    }

    private void init() {
        conversations = new ArrayList<>();
        conversationsAdapter = new RecentConversationsAdapter(conversations, this);
        activityMessengerBinding.conversationsRecyclerview.setAdapter(conversationsAdapter);
        inTouchDataBase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    private void listenConversations() {
        inTouchDataBase.getReference(Constants.KEY_COLLECTION_CONVERSATIONS).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String currentUserId = mAuth.getCurrentUser().getUid().toString();
                String currentReceiverId = snapshot.child(Constants.KEY_RECEIVER_ID).getValue(String.class);
                String currentSenderId = snapshot.child(Constants.KEY_SENDER_ID).getValue(String.class);
                if(currentUserId.equals(currentSenderId) || currentUserId.equals(currentReceiverId)) {
                    ChatMessage chatMessage = new ChatMessage();
                    chatMessage.setSenderId(currentSenderId);
                    chatMessage.setReceiverId(currentReceiverId);
                    if(currentUserId.equals(currentSenderId)) {
                        chatMessage.setConversionName(snapshot.child(Constants.KEY_RECEIVER_NAME).getValue(String.class));
                        chatMessage.setConversionId(snapshot.child(Constants.KEY_RECEIVER_ID).getValue(String.class));
                    }
                    else {
                        chatMessage.setConversionName(snapshot.child(Constants.KEY_SENDER_NAME).getValue(String.class));
                        chatMessage.setConversionId(snapshot.child(Constants.KEY_SENDER_ID).getValue(String.class));
                    }
                    chatMessage.setMessage(snapshot.child(Constants.KEY_LAST_MESSAGE).getValue(String.class));
                    chatMessage.setDateObject(snapshot.child(Constants.KEY_TIMESTAMP).getValue(Date.class));
                    conversations.add(chatMessage);
                    Collections.sort(conversations, (obj1, obj2) -> obj2.getDateObject().compareTo(obj1.getDateObject()));
                    conversationsAdapter.notifyDataSetChanged();
                    activityMessengerBinding.conversationsRecyclerview.smoothScrollToPosition(0);
                }

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
    public void onConversionClicked(User user) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(Constants.KEY_USER, user);
        startActivity(intent);
    }

    public void onClickNewChat(View view) {
        startActivity(new Intent(this, ChatCreateActivity.class));
    }
}