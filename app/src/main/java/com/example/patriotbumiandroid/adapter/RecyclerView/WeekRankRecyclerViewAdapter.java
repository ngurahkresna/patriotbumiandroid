package com.example.patriotbumiandroid.adapter.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.patriotbumiandroid.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeekRankRecyclerViewAdapter extends RecyclerView.Adapter<WeekRankRecyclerViewAdapter.ViewHolder> {
    public String[] mDataSet;
    public String[] mDataSetXp;

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

    public WeekRankRecyclerViewAdapter(String[] myDataSet, String[] myDataSetXp) {
        mDataSet = myDataSet;
        mDataSetXp = myDataSetXp;
    }

    @NonNull
    @Override
    public WeekRankRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_rank, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(mDataSet[position]);
        holder.xp.setText(mDataSetXp[position]);
        holder.number.setText("#" + (position + 1));
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
