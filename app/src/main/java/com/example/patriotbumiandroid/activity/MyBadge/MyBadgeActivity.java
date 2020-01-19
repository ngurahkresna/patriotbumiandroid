package com.example.patriotbumiandroid.activity.MyBadge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.adapter.RecyclerView.MyBadgeRecyclerViewAdapter;

public class MyBadgeActivity extends AppCompatActivity {

    private ImageView back;
    private RecyclerView myBadgeRecyclerView;
    private GridLayoutManager gridLayoutManager;
    private MyBadgeRecyclerViewAdapter myBadgeRecyclerViewAdapter;
    private String[] mData = {"Superman", "Superman", "Batman"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_badge);

        back = findViewById(R.id.back);
        myBadgeRecyclerView = findViewById(R.id.myBadgeRecyclerView);

        initBadge();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initBadge() {
        gridLayoutManager = new GridLayoutManager(this, 2);
        myBadgeRecyclerViewAdapter = new MyBadgeRecyclerViewAdapter(mData);
        myBadgeRecyclerView.setHasFixedSize(true);
        myBadgeRecyclerView.setLayoutManager(gridLayoutManager);
        myBadgeRecyclerView.setAdapter(myBadgeRecyclerViewAdapter);
    }
}
