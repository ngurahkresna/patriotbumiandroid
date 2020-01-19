package com.example.patriotbumiandroid.model.Article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ArticleData {
    @SerializedName("articles")
    @Expose
    private List<Article> articleList = new ArrayList<>();

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
}
