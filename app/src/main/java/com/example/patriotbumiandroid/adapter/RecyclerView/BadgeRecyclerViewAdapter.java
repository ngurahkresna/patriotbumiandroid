package com.example.patriotbumiandroid.adapter.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.patriotbumiandroid.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BadgeRecyclerViewAdapter extends RecyclerView.Adapter<BadgeRecyclerViewAdapter.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView badgeImage;
        public ViewHolder(@NonNull View view) {
            super(view);
            badgeImage = view.findViewById(R.id.badge);
        }
    }

    public BadgeRecyclerViewAdapter() {

    }

    @NonNull
    @Override
    public BadgeRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.circle_badge, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
