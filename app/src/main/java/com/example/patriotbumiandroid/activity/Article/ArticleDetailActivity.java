package com.example.patriotbumiandroid.activity.Article;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.patriotbumiandroid.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ArticleDetailActivity extends AppCompatActivity {

    private ImageView backImage, articleImage;
    private TextView articleAuthor, articleDate, articleTitle, articleContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        backImage = findViewById(R.id.back);
        articleImage = findViewById(R.id.article_detail_image);
        articleAuthor = findViewById(R.id.article_detail_author);
        articleDate = findViewById(R.id.article_detail_date);
        articleTitle = findViewById(R.id.article_detail_title);
        articleContent = findViewById(R.id.article_detail_content);

        initArticle();

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initArticle() {
        Intent intent = getIntent();
        try {
            Picasso
                    .get()
                    .load(intent.getStringExtra("image"))
                    .into(articleImage, new Callback() {
                        @Override
                        public void onSuccess()
                        {
//                            holder.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d("picasso failed", e.toString());
//                            holder.progressBar.setVisibility(View.GONE);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        articleAuthor.setText("Authored By: " + intent.getStringExtra("author"));
        articleDate.setText("Uploaded At: " + "25 September 2019");
        articleTitle.setText(intent.getStringExtra("title"));
        articleContent.setText(intent.getStringExtra("content"));
    }
}
