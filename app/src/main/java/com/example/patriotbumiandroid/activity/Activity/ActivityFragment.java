package com.example.patriotbumiandroid.activity.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.adapter.RecyclerView.ActivityRecyclerViewAdapter;
import com.example.patriotbumiandroid.data.APIService;
import com.example.patriotbumiandroid.data.APIUtils;
import com.example.patriotbumiandroid.model.Activity.Activity;

import java.util.List;

public class ActivityFragment extends Fragment {

    private APIService mAPIService;
    private RecyclerView specialRecyclerView, everydayRecyclerView, bonusRecyclerView;
    private LinearLayoutManager linearLayoutManager1, linearLayoutManager2, linearLayoutManager3;
    private ActivityRecyclerViewAdapter activityRecyclerViewAdapter;

    public ActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        specialRecyclerView = view.findViewById(R.id.activity_special_recyclerview);
        everydayRecyclerView = view.findViewById(R.id.activity_everyday_recyclerview);
        bonusRecyclerView = view.findViewById(R.id.activity_bonus_recyclerview);
        linearLayoutManager1 = new LinearLayoutManager(this.getActivity());
        linearLayoutManager2 = new LinearLayoutManager(this.getActivity());
        linearLayoutManager3 = new LinearLayoutManager(this.getActivity());

        mAPIService = APIUtils.getAPIService();

        initSpecialData();
        initEverydayData();
        initBonusData();
    }

    private void initSpecialData() {
        Call<List<Activity>> activityDataCall = mAPIService.getActivityOffset("0");
        activityDataCall.enqueue(new Callback<List<Activity>>() {
            @Override
            public void onResponse(Call<List<Activity>> call, Response<List<Activity>> response) {
                initSpecial(response.body());
                Log.d("getActivity", response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Activity>> call, Throwable t) {
                Log.d("getActivity", t.toString());
            }
        });
    }

    private void initSpecial(List<Activity> body) {
        activityRecyclerViewAdapter = new ActivityRecyclerViewAdapter(body);
        specialRecyclerView.setHasFixedSize(true);
        specialRecyclerView.setLayoutManager(linearLayoutManager1);
        specialRecyclerView.setAdapter(activityRecyclerViewAdapter);
    }

    private void initEverydayData() {
        Call<List<Activity>> activityDataCall = mAPIService.getActivityOffset("2");
        activityDataCall.enqueue(new Callback<List<Activity>>() {
            @Override
            public void onResponse(Call<List<Activity>> call, Response<List<Activity>> response) {
                initEveryday(response.body());
                Log.d("getActivity", response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Activity>> call, Throwable t) {
                Log.d("getActivity", t.toString());
            }
        });
    }

    private void initEveryday(List<Activity> body) {
        activityRecyclerViewAdapter = new ActivityRecyclerViewAdapter(body);
        everydayRecyclerView.setHasFixedSize(true);
        everydayRecyclerView.setLayoutManager(linearLayoutManager2);
        everydayRecyclerView.setAdapter(activityRecyclerViewAdapter);
    }

    private void initBonusData() {
        Call<List<Activity>> activityDataCall = mAPIService.getActivityOffset("4");
        activityDataCall.enqueue(new Callback<List<Activity>>() {
            @Override
            public void onResponse(Call<List<Activity>> call, Response<List<Activity>> response) {
                initBonus(response.body());
                Log.d("getActivity", response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Activity>> call, Throwable t) {
                Log.d("getActivity", t.toString());
            }
        });
    }

    private void initBonus(List<Activity> body) {
        activityRecyclerViewAdapter = new ActivityRecyclerViewAdapter(body);
        bonusRecyclerView.setHasFixedSize(true);
        bonusRecyclerView.setLayoutManager(linearLayoutManager3);
        bonusRecyclerView.setAdapter(activityRecyclerViewAdapter);
    }
}
