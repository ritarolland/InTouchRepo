package com.example.intouch.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.intouch.activities.ChatActivity;
import com.example.intouch.activities.ChatCreateActivity;
import com.example.intouch.adapters.RecentConversationsAdapter;
import com.example.intouch.databinding.FragmentChatBinding;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment implements ConversionListener {

    private FragmentChatBinding fragmentChatBinding;

    private List<ChatMessage> conversations;
    private RecentConversationsAdapter conversationsAdapter;

    FirebaseDatabase inTouchDataBase;
    FirebaseAuth mAuth;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentChatBinding = FragmentChatBinding.inflate(inflater, container, false);

        setListeners();
        init();
        listenConversations();

        return fragmentChatBinding.getRoot();
    }

    private void setListeners() {
        fragmentChatBinding.floatingButton.setOnClickListener(v -> onClickNewChat());
    }

    private void init() {
        conversations = new ArrayList<>();
        conversationsAdapter = new RecentConversationsAdapter(conversations, this);
        fragmentChatBinding.conversationsRecyclerview.setAdapter(conversationsAdapter);
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
                        chatMessage.conversationImage = snapshot.child(Constants.KEY_RECEIVER_IMAGE).getValue(String.class);

                    }
                    else {
                        chatMessage.setConversionName(snapshot.child(Constants.KEY_SENDER_NAME).getValue(String.class));
                        chatMessage.setConversionId(snapshot.child(Constants.KEY_SENDER_ID).getValue(String.class));
                        chatMessage.conversationImage = snapshot.child(Constants.KEY_SENDER_IMAGE).getValue(String.class);
                    }
                    chatMessage.setMessage(snapshot.child(Constants.KEY_LAST_MESSAGE).getValue(String.class));
                    chatMessage.setDateObject(snapshot.child(Constants.KEY_TIMESTAMP).getValue(Date.class));
                    conversations.add(chatMessage);
                    Collections.sort(conversations, (obj1, obj2) -> obj2.getDateObject().compareTo(obj1.getDateObject()));
                    conversationsAdapter.notifyDataSetChanged();
                    fragmentChatBinding.conversationsRecyclerview.smoothScrollToPosition(0);
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
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        intent.putExtra(Constants.KEY_USER, user);
        startActivity(intent);
    }

    public void onClickNewChat() {
        startActivity(new Intent(getActivity(), ChatCreateActivity.class));

    }


}