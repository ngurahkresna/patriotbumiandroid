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
import com.example.patriotbumiandroid.adapter.RecyclerView.AllRankRecyclerViewAdapter;
import com.example.patriotbumiandroid.adapter.RecyclerView.MonthRankRecyclerViewAdapter;

public class MonthRankFragment extends Fragment {

    private RecyclerView monthRecyclerView;
    private MonthRankRecyclerViewAdapter monthRankRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private String[] mDataset = {"G N Kresna", "Fikri Miftah Maulana", "Rizqi Prima"};
    private String[] mDatasetXp = {"350", "300", "200"};

    public MonthRankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_month_rank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        monthRecyclerView = view.findViewById(R.id.allrank_recyclerview);
        linearLayoutManager = new LinearLayoutManager(this.getActivity());
        monthRecyclerView.setLayoutManager(linearLayoutManager);
        monthRankRecyclerViewAdapter = new MonthRankRecyclerViewAdapter(mDataset, mDatasetXp);
        monthRecyclerView.setAdapter(monthRankRecyclerViewAdapter);
    }
}
