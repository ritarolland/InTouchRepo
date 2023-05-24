package com.example.intouch.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.intouch.adapters.ChatAdapter;
import com.example.intouch.databinding.ActivityChatBinding;
import com.example.intouch.databinding.ActivityChatCreateBinding;
import com.example.intouch.models.ChatMessage;
import com.example.intouch.models.User;
import com.example.intouch.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ChatActivity extends AppCompatActivity {
    ActivityChatBinding activityChatBinding;
    private User receiverUser;

    private ChatAdapter chatAdapter;
    private FirebaseDatabase inTouchDatabase = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference databaseReference = inTouchDatabase.getReference(Constants.KEY_COLLECTION_CHAT);
    private List<ChatMessage> chatMessages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChatBinding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(activityChatBinding.getRoot());

        setListeners();
        loadReceiverDetails();
        init();


        databaseReference.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                /*DataSnapshot childSnapshot = snapshot;
                String senderId = childSnapshot.child(Constants.KEY_SENDER_ID).getValue().toString();
                String receiverId = childSnapshot.child(Constants.KEY_RECEIVER_ID).getValue().toString();
                String message = childSnapshot.child(Constants.KEY_MESSAGE).getValue().toString();
                //Date dateObject = (Date) childSnapshot.child(Constants.KEY_TIMESTAMP).getValue();
                ChatMessage chatMessage = new ChatMessage(senderId, receiverId, message, "now");
                chatMessages.add(chatMessage);*/
                chatMessages.add(new ChatMessage("1", "2", "message", "now"));
                //chatAdapter.notifyDataSetChanged();

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
        chatMessages.add(new ChatMessage("1", "2", "message", "now"));
        chatAdapter = new ChatAdapter(
                chatMessages,
                mAuth.getCurrentUser().getUid().toString()
        );
        activityChatBinding.chatRecyclerview.setAdapter(chatAdapter);
        //chatAdapter.notifyDataSetChanged();
        Integer i = chatMessages.size();
        String s = i.toString();
        Toast.makeText(getApplicationContext(), chatMessages.get(0).getMessage(), Toast.LENGTH_SHORT).show();

        //inTouchDatabase.getReference(Constants.KEY_COLLECTION_CHAT).addListenerForSingleValueEvent(eventListener);
    }

    private void init() {


    }

    private void sendMessage() {
        HashMap<String, Object> message = new HashMap<>();
        message.put(Constants.KEY_SENDER_ID, mAuth.getCurrentUser().getUid().toString());
        message.put(Constants.KEY_RECEIVER_ID, receiverUser.getId());
        message.put(Constants.KEY_MESSAGE, activityChatBinding.edChatInput.getText().toString());
        message.put(Constants.KEY_TIMESTAMP, new Date());
        inTouchDatabase.getReference(Constants.KEY_COLLECTION_CHAT).push().setValue(message);
        activityChatBinding.edChatInput.setText(null);
    }



   /* private final ValueEventListener eventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            *//*chatMessages.clear();
            for(DataSnapshot childSnapshot : snapshot.getChildren()) {
                String senderId = childSnapshot.child(Constants.KEY_SENDER_ID).getValue().toString();
                String receiverId = childSnapshot.child(Constants.KEY_RECEIVER_ID).getValue().toString();
                String message = childSnapshot.child(Constants.KEY_MESSAGE).getValue().toString();
                //Date dateObject = (Date) childSnapshot.child(Constants.KEY_TIMESTAMP).getValue();
                ChatMessage chatMessage = new ChatMessage(senderId, receiverId, message, "now");
                chatMessages.add(chatMessage);
            }*//*

            chatAdapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
*/


    private void loadReceiverDetails() {
        receiverUser = (User) getIntent().getSerializableExtra(Constants.KEY_USER);
        activityChatBinding.friendName.setText(receiverUser.getUserName());
    }

    private void setListeners() {
        activityChatBinding.frameInputButton.setOnClickListener(v -> sendMessage());

    }

    private String getReadableDateTime(Date date) {
        return new SimpleDateFormat("MMMM dd, yyyy - hh:mm a", Locale.getDefault()).format(date);
    }
}