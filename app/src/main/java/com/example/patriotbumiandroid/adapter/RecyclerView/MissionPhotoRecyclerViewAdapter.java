package com.example.patriotbumiandroid.adapter.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.activity.ActivityDetail.ActivityDetailActivity;
import com.example.patriotbumiandroid.model.UserActivity.UserActivityDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MissionPhotoRecyclerViewAdapter extends RecyclerView.Adapter<MissionPhotoRecyclerViewAdapter.ViewHolder> {
    public List<UserActivityDetail> userActivityDetails;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView missionDate, missionStatus;
        public ImageView missionPhoto;
        public ProgressBar progressBar;
        public Context context;
        public ViewHolder(@NonNull View view) {
            super(view);
//            missionDate = view.findViewById(R.id.missionDate);
            missionStatus = view.findViewById(R.id.missionStatus);
            missionPhoto = view.findViewById(R.id.missionUploaded);
            progressBar = view.findViewById(R.id.imageProgressBar);
            context = view.getContext();
        }
    }

    public MissionPhotoRecyclerViewAdapter(List<UserActivityDetail> myDataSet) {
        userActivityDetails = myDataSet;
    }

    @NonNull
    @Override
    public MissionPhotoRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_mission_uploaded, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
//        holder.missionDate.setText(userActivityDetails.get(position).getId());
        holder.missionStatus.setText(userActivityDetails.get(position).getStatus());
        try {
            Picasso
                    .get()
                    .load("https://patriot-bumi.artcart.id" + userActivityDetails.get(position).getImage())
                    .into(holder.missionPhoto, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess()
                        {
                             holder.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d("picasso failed", e.toString());
                             holder.progressBar.setVisibility(View.GONE);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return userActivityDetails.size();
    }
}
