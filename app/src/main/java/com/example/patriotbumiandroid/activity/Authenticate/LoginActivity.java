package com.example.patriotbumiandroid.activity.Authenticate;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.activity.PostAuthenticate.PostMainActivity;
import com.example.patriotbumiandroid.data.APIService;
import com.example.patriotbumiandroid.data.APIUtils;
import com.example.patriotbumiandroid.model.User.Login;
import com.example.patriotbumiandroid.model.User.LoginResponse;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText email, password;
    private LinearLayout registerPage;
    private ImageView back;
    private APIService mAPIService;
    private Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        back = findViewById(R.id.back);
        registerPage = findViewById(R.id.registerPage);

        mAPIService = APIUtils.getAPIService();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getValidate()) {
                    login();
                }
            }
        });

        registerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(view.getContext(), RegisterActivity.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private Boolean getValidate() {
        if (email.getText().toString().matches("")) {
            email.setError("Isi Email Kamu!");
            return false;
        }

        if (password.getText().toString().matches("")) {
            password.setError("Isi Password Kamu!");
            return false;
        }

        return true;
    }

    private void login() {
        final String mail = email.getText().toString();
        final String pass = password.getText().toString();
        mAPIService.login(mail, pass).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.code() == 200) {
                    login = response.body().getLogin();

                    SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences("Authenticate", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.putString(getString(R.string.token), login.getToken());
                    editor.putString("USERID", login.getId());
                    editor.putString("USERNAME", login.getUsername());
                    editor.apply();

                    startActivity(new Intent(getApplicationContext(), PostMainActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Email atau Password salah!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("login", t.toString());
            }
        });
    }
}
