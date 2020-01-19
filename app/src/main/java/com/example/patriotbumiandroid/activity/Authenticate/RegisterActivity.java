package com.example.patriotbumiandroid.activity.Authenticate;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.activity.PostAuthenticate.PostMainActivity;
import com.example.patriotbumiandroid.data.APIService;
import com.example.patriotbumiandroid.data.APIUtils;
import com.google.gson.JsonElement;

public class RegisterActivity extends AppCompatActivity {

    private APIService mAPIService;
    private LinearLayout loginPage;
    private ImageView back;
    private TextView username, email, password;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginPage = findViewById(R.id.loginPage);
        back = findViewById(R.id.back);
        registerButton = findViewById(R.id.register_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), LoginActivity.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mAPIService = APIUtils.getAPIService();
    }

    @SuppressLint("StaticFieldLeak")
    private void register() {
        final String usernameString = username.getText().toString();
        final String emailString = email.getText().toString();
        final String passwordString = password.getText().toString();
        mAPIService.register(usernameString, emailString, passwordString, passwordString).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.code() == 200) {
                    String token = null;
                    token = response.body().toString().substring(21);
                    String bearer = token.substring(0, (token.length() - 3));

//                            SharedPreferences sharedPreferences = RegisterActivity.this.getSharedPreferences("Authenticate", Context.MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.clear();
//                            editor.putString(getString(R.string.token), bearer);
//                            editor.apply();

                    Toast.makeText(getApplicationContext(), "Register Berhasil! ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Register Gagal! " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.d("register", t.toString());
            }
        });
    }
}
