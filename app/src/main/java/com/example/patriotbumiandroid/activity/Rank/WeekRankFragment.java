package com.example.patriotbumiandroid.activity.Rank;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.adapter.RecyclerView.MonthRankRecyclerViewAdapter;
import com.example.patriotbumiandroid.adapter.RecyclerView.WeekRankRecyclerViewAdapter;

public class WeekRankFragment extends Fragment {

    private RecyclerView weekRecyclerView;
    private WeekRankRecyclerViewAdapter weekRankRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private String[] mDataset = {"G N Kresna", "Rizqi Prima", "Fikri Miftah Maulana"};
    private String[] mDatasetXp = {"350", "200", "100"};

    public WeekRankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_week_rank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        weekRecyclerView = view.findViewById(R.id.allrank_recyclerview);
        linearLayoutManager = new LinearLayoutManager(this.getActivity());
        weekRecyclerView.setLayoutManager(linearLayoutManager);
        weekRankRecyclerViewAdapter = new WeekRankRecyclerViewAdapter(mDataset, mDatasetXp);
        weekRecyclerView.setAdapter(weekRankRecyclerViewAdapter);
    }
}
