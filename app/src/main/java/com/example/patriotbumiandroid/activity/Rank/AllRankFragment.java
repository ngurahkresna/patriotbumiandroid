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
import com.example.patriotbumiandroid.model.Rank;

import java.util.List;

public class AllRankFragment extends Fragment {

    private RecyclerView allRecyclerView;
    private AllRankRecyclerViewAdapter allRankRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private String[] mDataset = {"Fikri Miftah Maulana", "G N Kresna","Rizqi Prima"};
    private String[] mDatasetXp = {"500", "450","300"};

    public AllRankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_rank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allRecyclerView = view.findViewById(R.id.allrank_recyclerview);
        linearLayoutManager = new LinearLayoutManager(this.getActivity());
        allRecyclerView.setLayoutManager(linearLayoutManager);
        allRankRecyclerViewAdapter = new AllRankRecyclerViewAdapter(mDataset, mDatasetXp);
        allRecyclerView.setAdapter(allRankRecyclerViewAdapter);
    }
}
