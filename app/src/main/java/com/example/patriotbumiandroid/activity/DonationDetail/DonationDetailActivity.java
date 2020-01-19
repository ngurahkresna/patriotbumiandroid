package com.example.patriotbumiandroid.activity.DonationDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.patriotbumiandroid.R;

public class DonationDetailActivity extends AppCompatActivity {

    private WebView webView;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_detail);

        webView = findViewById(R.id.donationWebView);
        back = findViewById(R.id.back);

        Intent intent = getIntent();

        webView.loadUrl(intent.getStringExtra("url"));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
