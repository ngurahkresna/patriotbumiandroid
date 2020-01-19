package com.example.patriotbumiandroid.activity.Article;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.adapter.RecyclerView.ArticleListRecyclerViewAdapter;
import com.example.patriotbumiandroid.data.APIService;
import com.example.patriotbumiandroid.data.APIUtils;
import com.example.patriotbumiandroid.model.Article.Article;
import com.example.patriotbumiandroid.model.Article.ArticleData;

import java.util.List;

public class ArticleListActivity extends AppCompatActivity {
    private APIService mAPIService;
    private ArticleListRecyclerViewAdapter articleListRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView articleRecyclerView;
    private String[] mDataset = {"Greta Thunberg dan Geraman 'How Dare You' yang Mendunia", "Pemanasan Global, Mempercepat Es Dunia Meleleh dan Mengancam Kehidupan"};
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        articleRecyclerView = findViewById(R.id.article_list_recyclerview);
        back = findViewById(R.id.back);

        loadArticle();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void loadArticle() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mAPIService = APIUtils.getAPIService();
                Call<List<Article>> articleDataCall = mAPIService.getArticle();
                articleDataCall.enqueue(new Callback<List<Article>>() {
                    @Override
                    public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                        initArticle(response.body());
                        Log.d("getArticle", response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<List<Article>> call, Throwable t) {
                        Log.d("getArticle", t.toString());
                    }
                });
                return null;
            }
        }.execute();
    }

    private void initArticle(List<Article> body) {
        articleRecyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        articleRecyclerView.setLayoutManager(linearLayoutManager);
        articleListRecyclerViewAdapter = new ArticleListRecyclerViewAdapter(body);
        articleRecyclerView.setAdapter(articleListRecyclerViewAdapter);
    }
}
