package com.example.patriotbumiandroid.adapter.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.activity.ActivityDetail.ActivityDetailActivity;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ActivityRecyclerViewAdapter extends RecyclerView.Adapter<ActivityRecyclerViewAdapter.ViewHolder> {
    public List<com.example.patriotbumiandroid.model.Activity.Activity> activity;
//    private File output = null;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView activityName;
//        public ImageView camera;
        public LinearLayout cardActivity;
        public Context context;
        public ViewHolder(@NonNull View view) {
            super(view);
            activityName = view.findViewById(R.id.activity_name);
//            camera = view.findViewById(R.id.activity_confirm_button);
            cardActivity = view.findViewById(R.id.cardActivity);
            context = view.getContext();
        }
    }

    public ActivityRecyclerViewAdapter(List<com.example.patriotbumiandroid.model.Activity.Activity> activities) {
        activity = activities;
    }

    @NonNull
    @Override
    public ActivityRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_activity, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.activityName.setText(activity.get(position).getTitle());
        holder.cardActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(holder.context, ActivityDetailActivity.class));
                intent.putExtra("id", activity.get(position).getId());
                holder.context.startActivity(intent);
            }
        });
//        holder.camera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////                File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
////                output = new File(file, "Tes.jpg");
////                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));
//                intent.putExtra("file", output);
//                SharedPreferences sharedPreferences = holder.context.getSharedPreferences("Progress", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.clear();
//                editor.putString("addProgress", String.valueOf(50));
//                editor.apply();
//                ((Activity) holder.context).startActivityForResult(intent, 1337);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return activity.size();
    }
}
