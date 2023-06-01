package com.example.intouch.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intouch.databinding.EventContainerItemBinding;
import com.example.intouch.listeners.EventTouchListener;
import com.example.intouch.models.Event;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder>{

    private final List<Event> events;
    private final EventTouchListener eventTouchListener;

    public EventsAdapter(List<Event> events, EventTouchListener eventTouchListener) {
        this.events = events;
        this.eventTouchListener = eventTouchListener;
    }

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventsViewHolder(
                EventContainerItemBinding.inflate(
                        LayoutInflater.from(parent.getContext())
                ,parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, int position) {
        holder.setData(events.get(position));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class EventsViewHolder extends RecyclerView.ViewHolder{
        EventContainerItemBinding binding;

        EventsViewHolder(EventContainerItemBinding eventContainerItemBinding) {
            super(eventContainerItemBinding.getRoot());
            binding = eventContainerItemBinding;
        }

        void setData(Event event) {
            binding.eventName.setText(event.eventName);
            binding.eventDescription.setText(event.eventDescription);
            binding.getRoot().setOnClickListener(v -> eventTouchListener.onEventClick(event));
        }
    }
}
