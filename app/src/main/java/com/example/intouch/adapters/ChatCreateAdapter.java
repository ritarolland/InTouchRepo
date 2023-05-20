package com.example.intouch.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intouch.databinding.ChatItemBinding;
import com.example.intouch.listeners.UserListener;
import com.example.intouch.models.User;

import java.util.List;

public class ChatCreateAdapter extends RecyclerView.Adapter<ChatCreateAdapter.ViewHolder>{

    private final List<User> users;
    private final UserListener userListener;


    public ChatCreateAdapter(List<User> users, UserListener userListener) {
        this.users = users;
        this.userListener = userListener;
    }

    @NonNull
    @Override
    public ChatCreateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChatItemBinding chatItemBinding = ChatItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(chatItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatCreateAdapter.ViewHolder holder, int position) {
        User user = users.get(position);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ChatItemBinding binding;
        ViewHolder(ChatItemBinding chatItemBinding){
            super(chatItemBinding.getRoot());
            binding = chatItemBinding;

        }
        void setUserData(User user) {
            binding.avatar.setImageResource(user.getAvatar());
            binding.name.setText(user.getUserName());
            binding.getRoot().setOnClickListener(v -> userListener.onUserClick(user));
        }

    }
}
