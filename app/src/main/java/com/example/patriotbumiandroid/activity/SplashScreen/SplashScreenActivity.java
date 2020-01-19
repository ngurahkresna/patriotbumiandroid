package com.example.patriotbumiandroid.activity.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.activity.PreAuthenticate.MainActivity;
import com.example.patriotbumiandroid.activity.PostAuthenticate.PostMainActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = SplashScreenActivity.this.getSharedPreferences("Authenticate", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("TOKEN", "");
                if (token.equals(null) || token == "") {
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(SplashScreenActivity.this, PostMainActivity.class));
                }
                finish();
            }
        },3000);
    }
}
