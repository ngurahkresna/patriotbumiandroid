package com.example.patriotbumiandroid.activity.TodayAction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.adapter.RecyclerView.ActivityRecyclerViewAdapter;

public class TodayActionActivity extends AppCompatActivity {

    private ImageView back;
    private RecyclerView actionTodayRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ActivityRecyclerViewAdapter activityRecyclerViewAdapter;
    private String[] mDataset = {"Mandi menggunakan Shower", "Buang Sampah Pada Tempatnya", "Mematikan Lampu Saat Tidak Digunakan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_action);

        back = findViewById(R.id.back);
        actionTodayRecyclerView = findViewById(R.id.actionTodayRecyclerView);

        initActionToday();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initActionToday() {
        linearLayoutManager = new LinearLayoutManager(this);
//        activityRecyclerViewAdapter = new ActivityRecyclerViewAdapter(mDataset);
        actionTodayRecyclerView.setHasFixedSize(true);
        actionTodayRecyclerView.setLayoutManager(linearLayoutManager);
        actionTodayRecyclerView.setAdapter(activityRecyclerViewAdapter);
    }
}
