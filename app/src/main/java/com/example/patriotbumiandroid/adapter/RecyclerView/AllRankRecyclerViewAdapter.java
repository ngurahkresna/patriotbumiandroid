package com.example.patriotbumiandroid.adapter.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.model.Rank;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllRankRecyclerViewAdapter extends RecyclerView.Adapter<AllRankRecyclerViewAdapter.ViewHolder> {
    public String[] dataSet;
    public String[] dataSetXp;

    public AllRankRecyclerViewAdapter(String[] mDataset, String[] mDatasetXp) {
        dataSet = mDataset;
        dataSetXp = mDatasetXp;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, xp, number;
        public Context context;
        public ViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.rank_name);
            xp = view.findViewById(R.id.rank_xp);
            number = view.findViewById(R.id.rank_number);
            context = view.getContext();
        }
    }

    @NonNull
    @Override
    public AllRankRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_rank, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(dataSet[position]);
        holder.xp.setText(dataSetXp[position]);
        holder.number.setText("#" + (position + 1));
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }
}
