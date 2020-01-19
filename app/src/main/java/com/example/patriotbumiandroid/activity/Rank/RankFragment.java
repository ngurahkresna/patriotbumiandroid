package com.example.patriotbumiandroid.activity.Rank;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.adapter.ViewPager.RankViewPagerAdapter;
import com.example.patriotbumiandroid.data.APIClient;
import com.example.patriotbumiandroid.data.APIService;
import com.example.patriotbumiandroid.data.APIUtils;
import com.example.patriotbumiandroid.model.Rank;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class RankFragment extends Fragment {

    private ViewPager rankViewPager;
    private TabLayout rankTabLoyout;
    private RankViewPagerAdapter rankViewPagerAdapter;
    private APIService apiService;
    private List<Rank> rankList;

    public RankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rankViewPager = view.findViewById(R.id.viewpager_rank);
        rankTabLoyout = view.findViewById(R.id.rank_tablayout);

        apiService = APIUtils.getAPIService();

        initRank();
    }

//    private void initRankData() {
//        Call<List<Rank>> listCall = apiService.getRank();
//        listCall.enqueue(new Callback<List<Rank>>() {
//            @Override
//            public void onResponse(Call<List<Rank>> call, Response<List<Rank>> response) {
//                rankList = response.body();
////                initRank(rankList);
//            }
//
//            @Override
//            public void onFailure(Call<List<Rank>> call, Throwable t) {
//
//            }
//        });
//    }

    private void initRank() {
        rankViewPagerAdapter = new RankViewPagerAdapter(getChildFragmentManager());
        rankViewPager.setAdapter(rankViewPagerAdapter);
        rankTabLoyout.setupWithViewPager(rankViewPager);
    }
}
