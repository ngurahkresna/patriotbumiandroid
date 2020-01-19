package com.example.patriotbumiandroid.adapter.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.activity.Article.ArticleDetailActivity;
import com.example.patriotbumiandroid.data.APIService;
import com.example.patriotbumiandroid.model.Article.Article;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.ListFragment;

public class ArticleViewPagerAdapter extends FragmentStatePagerAdapter {
    private static List<Article> articleList;

    public ArticleViewPagerAdapter(@NonNull FragmentManager fm, List<Article> articles) {
        super(fm);
        articleList = articles;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        String title = articleList.get(position).getTitle();
        String content = articleList.get(position).getContent();
        String image = "https://patriot-bumi.artcart.id" + articleList.get(position).getImage();
        String author = articleList.get(position).getAuthor();

        return ArrayListFragment.newInstance(position, title, content, image, author);
    }

    @Override
    public int getCount() {
        return articleList.size();
    }

    public static class ArrayListFragment extends ListFragment {

        static String articleTitle, articleContent, articleImage, articleAuthor;

        static ArrayListFragment newInstance(int num, String titles, String contents, String images, String author) {
            ArrayListFragment f = new ArrayListFragment();

            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);

            articleTitle = titles;
            articleContent = contents;
            articleImage = images;
            articleAuthor = author;

            return f;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.card_article, container, false);
            TextView title = v.findViewById(R.id.article_title);
            TextView highlight = v.findViewById(R.id.article_highlight);
            ImageView image = v.findViewById(R.id.article_image);
            final ProgressBar progressBar = v.findViewById(R.id.progressBar);
            ((TextView)title).setText(articleTitle);
            ((TextView)highlight).setText(articleContent);
            try {
                Picasso
                        .get()
                        .load(articleImage)
                        .into((ImageView)image, new Callback() {
                            @Override
                            public void onSuccess()
                            {
                                progressBar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(Exception e) {
                                Log.d("picasso failed", e.toString());
                                progressBar.setVisibility(View.GONE);
                            }
                        });
            } catch (Exception e) {
                e.printStackTrace();
            }

            final Context context = v.getContext();
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ArticleDetailActivity.class);
                    intent.putExtra("title", articleTitle);
                    intent.putExtra("content", articleContent);
                    intent.putExtra("image", articleImage);
                    intent.putExtra("author", articleAuthor);
                    context.startActivity(intent);
                }
            });
            return v;
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
        }
    }
}
