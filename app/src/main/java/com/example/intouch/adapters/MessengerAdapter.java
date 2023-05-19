package com.example.intouch.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.intouch.databinding.ChatItemBinding;
import com.example.intouch.models.User;

import java.util.List;

public class MessengerAdapter extends RecyclerView.Adapter<MessengerAdapter.ViewHolder>{
    private final LayoutInflater inflater;
    private final List<User> chats;


    public MessengerAdapter(LayoutInflater inflater, List<User> chats) {
        this.inflater = inflater;
        this.chats = chats;
    }

    @NonNull
    @Override
    public MessengerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChatItemBinding chatItemBinding = ChatItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(chatItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MessengerAdapter.ViewHolder holder, int position) {
        User chat = chats.get(position);
        holder.avatarView.setImageResource(chat.getAvatar());
        holder.nameView.setText(chat.getUserName());
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ChatItemBinding binding;
        final ImageView avatarView;
        final TextView nameView;
        ViewHolder(ChatItemBinding chatItemBinding){
            super(chatItemBinding.getRoot());
            binding = chatItemBinding;
            avatarView = chatItemBinding.avatar;
            nameView = chatItemBinding.name;
        }
    }
}
