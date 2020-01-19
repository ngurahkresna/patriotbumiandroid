package com.example.patriotbumiandroid.adapter.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.activity.ActivityDetail.ActivityDetailActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyBadgeRecyclerViewAdapter extends RecyclerView.Adapter<MyBadgeRecyclerViewAdapter.ViewHolder> {
    public String[] mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView badgeTitle;
        public ImageView badgeImage;
        public Context context;
        public ViewHolder(@NonNull View view) {
            super(view);
            badgeTitle = view.findViewById(R.id.badgeTitle);
            badgeImage = view.findViewById(R.id.badgeImage);
            context = view.getContext();
        }
    }

    public MyBadgeRecyclerViewAdapter(String[] myDataSet) {
        mDataSet = myDataSet;
    }

    @NonNull
    @Override
    public MyBadgeRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_badge, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.badgeTitle.setText(mDataSet[position]);
//        holder.badgeImage.setImageResource();
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
