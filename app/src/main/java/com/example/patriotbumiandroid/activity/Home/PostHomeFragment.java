package com.example.patriotbumiandroid.activity.Home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.activity.Article.ArticleListActivity;
import com.example.patriotbumiandroid.adapter.RecyclerView.ActivityRecyclerViewAdapter;
import com.example.patriotbumiandroid.adapter.RecyclerView.BadgeRecyclerViewAdapter;
import com.example.patriotbumiandroid.adapter.ViewPager.ArticleViewPagerAdapter;
import com.example.patriotbumiandroid.data.APIService;
import com.example.patriotbumiandroid.data.APIUtils;
import com.example.patriotbumiandroid.model.Activity.Activity;
import com.example.patriotbumiandroid.model.Article.Article;
import com.example.patriotbumiandroid.model.Rank;
import com.example.patriotbumiandroid.model.User.Register;
import com.example.patriotbumiandroid.model.User.RegisterResponse;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class PostHomeFragment extends Fragment  {

    private APIService mAPIService;
    private ViewPager articleViewPager;
    private TabLayout articleTabLayout;
    private RecyclerView activityRecyclerView, badgeRecyclerView;
    private ArticleViewPagerAdapter articleViewPagerAdapter;
    private RecyclerView.LayoutManager layoutManager, layoutManagerBadge;
    private ActivityRecyclerViewAdapter activityRecyclerViewAdapter;
    private BadgeRecyclerViewAdapter badgeRecyclerViewAdapter;
    private ImageView articleMore;
    private FragmentManager fragmentManager;
    private TextView homeName, homeXp, homeCoin;
    private Register register;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar articleProgressBar, taskProgressBar;

    public PostHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        articleViewPager = this.getActivity().findViewById(R.id.home_article);
        articleTabLayout = this.getActivity().findViewById(R.id.home_tablayout);
        activityRecyclerView = this.getActivity().findViewById(R.id.daftar_tugas_recyclerview);
        activityRecyclerView = this.getActivity().findViewById(R.id.daftar_tugas_recyclerview);
        articleMore = this.getActivity().findViewById(R.id.article_more);
        badgeRecyclerView = this.getActivity().findViewById(R.id.home_badge);
        homeXp = this.getActivity().findViewById(R.id.home_xp);
        homeCoin = this.getActivity().findViewById(R.id.home_coin);
        homeName = this.getActivity().findViewById(R.id.home_name);
        swipeRefreshLayout = this.getActivity().findViewById(R.id.swipeRefresh);
        articleProgressBar = this.getActivity().findViewById(R.id.articleProgressBar);
        taskProgressBar = this.getActivity().findViewById(R.id.taskProgressBar);

        mAPIService = APIUtils.getAPIService();
        initFragmentManager();
        initUserData();
        initBadge();
        initArticleData();
        initActivityData();

        articleMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(new Intent(view.getContext(), ArticleListActivity.class));
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        initUserData();
                        initBadge();
                        initArticleData();
                        initActivityData();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }

    private void initFragmentManager() {
        if (!isAdded()) return;
        fragmentManager = getChildFragmentManager();
    }

    private void initUserData() {
        final SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("Authenticate", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("TOKEN", "");
        String userId = sharedPreferences.getString("USERID", "");
        final Call<RegisterResponse> registerCall = mAPIService.getProfile("Bearer " + token);
        registerCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.code() == 200) {
                    register = response.body().getRegister();

                    homeName.setText(register.getUsername());
                } else {
                    Log.d("profile", response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.d("profile", t.toString());
            }
        });

        Call<List<Rank>> listCall = mAPIService.getRank("Bearer " + token, userId);
        listCall.enqueue(new Callback<List<Rank>>() {
            @Override
            public void onResponse(Call<List<Rank>> call, Response<List<Rank>> response) {
                if (response.code() == 200) {
                    homeXp.setText(response.body().get(0).getXp());
                    homeCoin.setText(response.body().get(0).getGold());
                } else {
                    Log.d("rank", response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<List<Rank>> call, Throwable t) {
                Log.d("rank", t.toString());
            }
        });
    }

    private void initBadge() {
        layoutManagerBadge = new LinearLayoutManager(this.getActivity(), RecyclerView.HORIZONTAL, false);
        badgeRecyclerViewAdapter = new BadgeRecyclerViewAdapter();
        badgeRecyclerView.setLayoutManager(layoutManagerBadge);
        badgeRecyclerView.setHasFixedSize(true);
        badgeRecyclerView.setAdapter(badgeRecyclerViewAdapter);

    }

    private void initArticleData() {
        Call<List<Article>> articleDataCall = mAPIService.getArticleOffset("0");
        articleDataCall.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                articleProgressBar.setVisibility(View.GONE);
                initArticle(response.body());
                Log.d("getArticle", response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Log.d("getArticle", t.toString());
            }
        });
    }

    private void initArticle(List<Article> body) {
        articleViewPagerAdapter = new ArticleViewPagerAdapter(fragmentManager, body);
        articleViewPager.setAdapter(articleViewPagerAdapter);
        articleTabLayout.setupWithViewPager(articleViewPager);
    }

    private void initActivityData() {
        Call<List<Activity>> activityDataCall = mAPIService.getActivityOffset("0");
        activityDataCall.enqueue(new Callback<List<Activity>>() {
            @Override
            public void onResponse(Call<List<Activity>> call, Response<List<Activity>> response) {
                taskProgressBar.setVisibility(View.GONE);
                initActivity(response.body());
                Log.d("getActivity", response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Activity>> call, Throwable t) {
                Log.d("getActivity", t.toString());
            }
        });
    }

    private void initActivity(List<Activity> body) {
        activityRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getActivity());
        activityRecyclerView.setLayoutManager(layoutManager);
        activityRecyclerViewAdapter = new ActivityRecyclerViewAdapter(body);
        activityRecyclerView.setAdapter(activityRecyclerViewAdapter);
    }
}