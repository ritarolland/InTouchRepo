package com.example.intouch.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.intouch.ChatFragment;
import com.example.intouch.TheMostMainActivity;
import com.example.intouch.adapters.ChatAdapter;
import com.example.intouch.databinding.ActivityChatBinding;
import com.example.intouch.models.ChatMessage;
import com.example.intouch.models.User;
import com.example.intouch.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class ChatActivity extends AppCompatActivity {
    private ActivityChatBinding activityChatBinding;
    private User receiverUser;

    private ChatAdapter chatAdapter;

    private FirebaseDatabase inTouchDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private List<ChatMessage> chatMessages;
    private String expectedSenderId;
    private String expectedReceiverId;

    private String conversionId = null;
    private String currentUserName;


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
                DataSnapshot childSnapshot = snapshot;
                String senderId = childSnapshot.child(Constants.KEY_SENDER_ID).getValue().toString();
                String receiverId = childSnapshot.child(Constants.KEY_RECEIVER_ID).getValue().toString();

                if ((senderId.equals(expectedSenderId) && receiverId.equals(expectedReceiverId))
                        || senderId.equals(expectedReceiverId) && receiverId.equals(expectedSenderId)) {
                    String message = childSnapshot.child(Constants.KEY_MESSAGE).getValue().toString();
                    Date dateObject = childSnapshot.child(Constants.KEY_TIMESTAMP).getValue(Date.class);
                    String dateTime = getReadableDateTime(dateObject);
                    ChatMessage chatMessage = new ChatMessage(senderId, receiverId, message, dateTime);
                    chatMessage.setDateObject(dateObject);
                    chatMessages.add(chatMessage);
                    chatAdapter.notifyDataSetChanged();
                    activityChatBinding.chatRecyclerview.smoothScrollToPosition(chatMessages.size() - 1);
                }
                if (conversionId == null) {
                    checkForConversation();
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

    private void init() {
        inTouchDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        databaseReference = inTouchDatabase.getReference(Constants.KEY_COLLECTION_CHAT);
        chatMessages = new ArrayList<>();
        chatAdapter = new ChatAdapter(
                chatMessages,
                mAuth.getCurrentUser().getUid().toString()
        );
        activityChatBinding.chatRecyclerview.setAdapter(chatAdapter);
        expectedSenderId = mAuth.getCurrentUser().getUid().toString();
        expectedReceiverId = receiverUser.getId();
        inTouchDatabase.getReference(Constants.KEY_COLLECTION_USERS).get()
                .addOnCompleteListener(task -> {


                    if (task.isSuccessful() && task.getResult() != null) {
                        String currentUserId = mAuth.getCurrentUser().getUid();

                        DataSnapshot dataSnapshot = task.getResult();
                        currentUserName = dataSnapshot.child(currentUserId).child(Constants.KEY_USER_NAME).getValue(String.class);


                    }
                });
    }

    private void sendMessage() {
        if (activityChatBinding.edChatInput.getText().toString().isEmpty()) return;
        HashMap<String, Object> message = new HashMap<>();
        message.put(Constants.KEY_SENDER_ID, mAuth.getCurrentUser().getUid().toString());
        message.put(Constants.KEY_RECEIVER_ID, receiverUser.getId());
        message.put(Constants.KEY_MESSAGE, activityChatBinding.edChatInput.getText().toString());
        message.put(Constants.KEY_TIMESTAMP, new Date());
        inTouchDatabase.getReference(Constants.KEY_COLLECTION_CHAT).push().setValue(message);

        if (conversionId != null) {
            updateConversion(activityChatBinding.edChatInput.getText().toString());
        } else {
            HashMap<String, Object> conversion = new HashMap<>();
            conversion.put(Constants.KEY_SENDER_ID, mAuth.getCurrentUser().getUid().toString());
            conversion.put(Constants.KEY_RECEIVER_ID, receiverUser.getId());
            conversion.put(Constants.KEY_SENDER_NAME, currentUserName);
            conversion.put(Constants.KEY_RECEIVER_NAME, receiverUser.getUserName());
            conversion.put(Constants.KEY_LAST_MESSAGE, activityChatBinding.edChatInput.getText().toString());
            conversion.put(Constants.KEY_TIMESTAMP, new Date());
            addConversion(conversion);

        }
        activityChatBinding.edChatInput.setText(null);
    }


    private void loadReceiverDetails() {
        receiverUser = (User) getIntent().getSerializableExtra(Constants.KEY_USER);
        activityChatBinding.friendName.setText(receiverUser.getUserName());
    }

    private void setListeners() {
        activityChatBinding.frameInputButton.setOnClickListener(v -> sendMessage());
        activityChatBinding.buttonBack.setOnClickListener(v -> goBack());
    }

    private String getReadableDateTime(Date date) {
        return new SimpleDateFormat("hh:mm a dd MMMM", Locale.getDefault()).format(date);
    }

    private void addConversion(HashMap<String, Object> conversion) {
        String newKey = inTouchDatabase.getReference(Constants.KEY_COLLECTION_CONVERSATIONS)
                .push().getKey();
        inTouchDatabase.getReference(Constants.KEY_COLLECTION_CONVERSATIONS).child(newKey).setValue(conversion)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        conversionId = newKey;
                    }
                });

    }

    private void updateConversion(String message) {
        DatabaseReference databaseReference =
                inTouchDatabase.getReference(Constants.KEY_COLLECTION_CONVERSATIONS).child(conversionId);
        Map<String, Object> updates = new HashMap<>();
        updates.put(Constants.KEY_LAST_MESSAGE, message);
        updates.put(Constants.KEY_TIMESTAMP, new Date());
        databaseReference.updateChildren(updates);
    }

    private void checkForConversation() {
        if (chatMessages.size() > 0) {
            checkForConversationRemotely(mAuth.getCurrentUser().getUid().toString(), receiverUser.getId());
        }
    }

    private void checkForConversationRemotely(String senderId, String receiverId) {
        inTouchDatabase.getReference(Constants.KEY_COLLECTION_CONVERSATIONS).get().
                addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null && task.getResult().getChildrenCount() > 0) {
                        for (DataSnapshot childSnapshot : task.getResult().getChildren()) {
                            String currentSenderId = childSnapshot.child(Constants.KEY_SENDER_ID).getValue(String.class);
                            String currentReceiverId = childSnapshot.child(Constants.KEY_RECEIVER_ID).getValue(String.class);

                            if (Objects.equals(currentReceiverId, receiverId) && Objects.equals(currentSenderId, senderId) ||
                                    Objects.equals(currentSenderId, receiverId) && Objects.equals(currentReceiverId, senderId)) {
                                conversionId = childSnapshot.getKey();
                            }
                        }

                    }
                });

    }

    private void goBack() {
        Intent intent = new Intent(this, TheMostMainActivity.class);
        intent.putExtra(Constants.KEY_FRAGMENT_TAG, "Messenger");
        startActivity(intent);
    }
}