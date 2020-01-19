package com.example.patriotbumiandroid.activity.Home;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.activity.Article.ArticleListActivity;
import com.example.patriotbumiandroid.activity.Authenticate.LoginActivity;
import com.example.patriotbumiandroid.adapter.RecyclerView.ActivityRecyclerViewAdapter;
import com.example.patriotbumiandroid.adapter.ViewPager.ArticleViewPagerAdapter;
import com.example.patriotbumiandroid.data.APIService;
import com.example.patriotbumiandroid.data.APIUtils;
import com.example.patriotbumiandroid.model.Activity.Activity;
import com.example.patriotbumiandroid.model.Article.Article;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class PreHomeFragment extends Fragment  {

    private APIService mAPIService;
    private ViewPager articleViewPager;
    private TabLayout articleTabLayout;
    private RecyclerView activityRecyclerView;
    private ArticleViewPagerAdapter articleViewPagerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ActivityRecyclerViewAdapter activityRecyclerViewAdapter;
    private ImageView articleMore;
    private Button loginButton;
    private FragmentManager fragmentManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar articleProgressBar, activityProgressBar;

    public PreHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        articleViewPager = this.getActivity().findViewById(R.id.home_article);
        articleTabLayout = this.getActivity().findViewById(R.id.home_tablayout);
        activityRecyclerView = this.getActivity().findViewById(R.id.daftar_tugas_recyclerview);
        activityRecyclerView = this.getActivity().findViewById(R.id.daftar_tugas_recyclerview);
        articleMore = this.getActivity().findViewById(R.id.article_more);
        loginButton = this.getActivity().findViewById(R.id.login_button);
        articleProgressBar = this.getActivity().findViewById(R.id.articleProgressBar);
        activityProgressBar = this.getActivity().findViewById(R.id.activityProgressBar);
        swipeRefreshLayout = this.getActivity().findViewById(R.id.swipeRefresh);

        mAPIService = APIUtils.getAPIService();
        initFragmentManager();
        initArticleData();
        initActivityData();

        articleMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(new Intent(view.getContext(), ArticleListActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(new Intent(view.getContext(), LoginActivity.class));
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initArticleData();
                initActivityData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initFragmentManager() {
        if (!isAdded()) return;
        fragmentManager = getChildFragmentManager();
    }

    private void initArticleData() {
        Call<List<Article>> articleDataCall = mAPIService.getArticleOffset("0");
        articleDataCall.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                initArticle(response.body());
                articleProgressBar.setVisibility(View.GONE);
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
                initActivity(response.body());
                activityProgressBar.setVisibility(View.GONE);
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
