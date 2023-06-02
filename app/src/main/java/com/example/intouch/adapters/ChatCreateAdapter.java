package com.example.intouch.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intouch.databinding.ChatItemBinding;
import com.example.intouch.listeners.UserListener;
import com.example.intouch.models.User;

import java.util.List;

public class ChatCreateAdapter extends RecyclerView.Adapter<ChatCreateAdapter.ViewHolder>{

    private final List<User> users;
    private final UserListener userListener;

    private Bitmap getProfileImage(String encodedImage) {
        byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public ChatCreateAdapter(List<User> users, UserListener userListener) {
        this.users = users;
        this.userListener = userListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChatItemBinding chatItemBinding = ChatItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(chatItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setUserData(users.get(position));
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
            binding.avatar.setImageBitmap(getProfileImage(user.profileImage));
            binding.getRoot().setOnClickListener(v -> userListener.onUserClick(user));
        }

    }
}
