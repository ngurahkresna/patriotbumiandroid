package com.example.patriotbumiandroid.adapter.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.patriotbumiandroid.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PromoRecyclerViewAdapter extends RecyclerView.Adapter<PromoRecyclerViewAdapter.ViewHolder> {
    public String[] mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView promo;
        public ViewHolder(@NonNull View view) {
            super(view);
            promo = view.findViewById(R.id.promo_title);
        }
    }

    public PromoRecyclerViewAdapter(String[] myDataSet) {
        mDataSet = myDataSet;
    }

    @NonNull
    @Override
    public PromoRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_promo, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.promo.setText(mDataSet[position]);
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
