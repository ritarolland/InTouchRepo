package com.example.intouch.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intouch.databinding.ItemContainerResievedMessageBinding;
import com.example.intouch.databinding.ItemContainerSentMessageBinding;
import com.example.intouch.models.ChatMessage;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ChatMessage> chatMessages;
    private final String senderId;
    private final Bitmap receiverProfileImage;

    public static final int VIEW_TYPE_SENT = 1;
    public static final int VIEW_TYPE_RECEIVED = 2;


    public ChatAdapter(List<ChatMessage> chatMessages, String senderId, Bitmap receiverProfileImage) {
        this.chatMessages = chatMessages;
        this.senderId = senderId;
        this.receiverProfileImage = receiverProfileImage;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == VIEW_TYPE_SENT) {
            return new SentMessageViewHolder(ItemContainerSentMessageBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false));
        } else {
            return new ReceivedMessageHolder(ItemContainerResievedMessageBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == VIEW_TYPE_SENT) {
            ((SentMessageViewHolder) holder).setData(chatMessages.get(position));
        } else {
            ((ReceivedMessageHolder) holder).setData(chatMessages.get(position), receiverProfileImage);
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(chatMessages.get(position).getSenderId().equals(senderId)) {
            return VIEW_TYPE_SENT;
        } else {
            return VIEW_TYPE_RECEIVED;
        }
    }

    static class SentMessageViewHolder extends RecyclerView.ViewHolder {

        private final ItemContainerSentMessageBinding binding;

        SentMessageViewHolder(ItemContainerSentMessageBinding itemContainerSentMessageBinding) {
            super(itemContainerSentMessageBinding.getRoot());
            binding = itemContainerSentMessageBinding;
        }

        void setData(ChatMessage chatMessage) {
            binding.message.setText(chatMessage.getMessage());
            binding.dataTime.setText(chatMessage.getDateTime());
        }

    }
    static class ReceivedMessageHolder extends RecyclerView.ViewHolder {

        private final ItemContainerResievedMessageBinding binding;

        ReceivedMessageHolder(ItemContainerResievedMessageBinding itemContainerResievedMessageBinding) {
            super(itemContainerResievedMessageBinding.getRoot());
            binding = itemContainerResievedMessageBinding;
        }

        void setData(ChatMessage chatMessage, Bitmap receiverProfileImage) {
            binding.message.setText(chatMessage.getMessage());
            binding.dataTime.setText(chatMessage.getDateTime());
            binding.receiverImage.setImageBitmap(receiverProfileImage);
        }

    }
}
