package com.example.patriotbumiandroid.adapter.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.model.Article.Article;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleListRecyclerViewAdapter extends RecyclerView.Adapter<ArticleListRecyclerViewAdapter.ViewHolder> {
    public List<Article> article;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView articleTitle, articleContent;
        public ImageView articleImage;
        public ProgressBar progressBar;
        public Context context;
        public ViewHolder(@NonNull View view) {
            super(view);
            articleTitle = view.findViewById(R.id.article_title);
            articleContent = view.findViewById(R.id.article_highlight);
            articleImage = view.findViewById(R.id.article_image);
            progressBar = view.findViewById(R.id.articleProgressBar);
            context = view.getContext();
        }
    }

    public ArticleListRecyclerViewAdapter(List<Article> articles) {
        article = articles;
    }

    @NonNull
    @Override
    public ArticleListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_article_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.articleTitle.setText(article.get(position).getTitle());
        holder.articleContent.setText(article.get(position).getContent());
        try {
            Picasso
                    .get()
                    .load("https://www.patriotbumi.foodritious.com/public/" + article.get(position).getImage())
                    .into(holder.articleImage, new Callback() {
                        @Override
                        public void onSuccess()
                        {
                            holder.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d("picasso failed", e.toString());
                            holder.progressBar.setVisibility(View.GONE);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return article.size();
    }
}
