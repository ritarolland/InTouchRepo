package com.example.intouch.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intouch.databinding.ItemContainerEventCreatorBinding;
import com.example.intouch.databinding.ItemContainerEventMemberBinding;
import com.example.intouch.listeners.EventTouchListener;
import com.example.intouch.models.Event;

import java.util.List;

public class MyEventsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Event> myEvents;
    private final String currentUserId;
    private static EventTouchListener eventTouchListener;

    public static final int VIEW_TYPE_CREATOR = 1;
    public static final int VIEW_TYPE_MEMBER = 2;

    public MyEventsAdapter(List<Event> myEvents, String currentUserId, EventTouchListener eventTouchListener) {
        this.myEvents = myEvents;
        this.currentUserId = currentUserId;
        this.eventTouchListener = eventTouchListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_CREATOR) {
            return new EventCreatorViewHolder(ItemContainerEventCreatorBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false));
        } else {
            return new EventMemberViewHolder(ItemContainerEventMemberBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == VIEW_TYPE_CREATOR) {
            ((EventCreatorViewHolder) holder).setData(myEvents.get(position));
        } else {
            ((EventMemberViewHolder) holder).setData(myEvents.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return myEvents.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(myEvents.get(position).creatorId.equals(currentUserId)) {
            return VIEW_TYPE_CREATOR;
        } else return VIEW_TYPE_MEMBER;
    }

    static class EventCreatorViewHolder extends RecyclerView.ViewHolder{
        ItemContainerEventCreatorBinding binding;
        EventCreatorViewHolder(ItemContainerEventCreatorBinding itemContainerEventCreatorBinding) {
            super(itemContainerEventCreatorBinding.getRoot());
            binding = itemContainerEventCreatorBinding;
        }
        void setData(Event event) {
            binding.eventName.setText(event.eventName);
            binding.eventDescription.setText(event.eventDescription);
            binding.getRoot().setOnClickListener(v -> eventTouchListener.onEventClick(event));
        }
    }

    static class EventMemberViewHolder extends RecyclerView.ViewHolder{
        ItemContainerEventMemberBinding binding;
        EventMemberViewHolder(ItemContainerEventMemberBinding itemContainerEventMemberBinding) {
            super(itemContainerEventMemberBinding.getRoot());
            binding = itemContainerEventMemberBinding;
        }
        void setData(Event event) {
            binding.eventName.setText(event.eventName);
            binding.eventDescription.setText(event.eventDescription);
            binding.getRoot().setOnClickListener(v -> eventTouchListener.onEventClick(event));
        }
    }
}
