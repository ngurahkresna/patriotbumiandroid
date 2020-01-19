package com.example.patriotbumiandroid.activity.Profile;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.activity.PreAuthenticate.MainActivity;
import com.example.patriotbumiandroid.activity.MyBadge.MyBadgeActivity;
import com.example.patriotbumiandroid.activity.TodayAction.TodayActionActivity;
import com.example.patriotbumiandroid.data.APIService;
import com.example.patriotbumiandroid.data.APIUtils;
import com.example.patriotbumiandroid.model.Rank;
import com.example.patriotbumiandroid.model.User.Register;
import com.example.patriotbumiandroid.model.User.RegisterResponse;

import java.util.List;

public class ProfileFragment extends Fragment {

    private LinearLayout actionLinearLayout, badgeLinearLayout;
    private TextView profileName, logout, profile_xp, profile_coin;
    private Button xpButton;
    private APIService mAPIService;
    private Register register;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        actionLinearLayout = view.findViewById(R.id.profile_action);
        badgeLinearLayout = view.findViewById(R.id.profile_badge);
        xpButton = view.findViewById(R.id.get_xp);
        profileName = view.findViewById(R.id.profile_name);
        profile_xp = view.findViewById(R.id.profile_xp);
        profile_coin = view.findViewById(R.id.profile_coin);
        logout = view.findViewById(R.id.logout);

        mAPIService = APIUtils.getAPIService();

        initUserData();

        xpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        actionLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), TodayActionActivity.class));
            }
        });

        badgeLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), MyBadgeActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Authenticate", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("TOKEN", "");
                mAPIService.logout("Bearer " + token).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code() == 200) {
                            getActivity().getSharedPreferences("Authenticate", Context.MODE_PRIVATE).edit().remove("TOKEN").apply();
                            startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                        } else {
                            Toast.makeText(getContext(), "Logout Failed! " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("logout", t.toString());
                    }
                });
            }
        });
    }

    private void initUserData() {
        final SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("Authenticate", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("TOKEN", "");
        String userId = sharedPreferences.getString("USERID", "");
        final Call<RegisterResponse> registerCall = mAPIService.getProfile("Bearer " + token);
        registerCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.code() == 200) {
                    register = response.body().getRegister();

                    profileName.setText(register.getUsername());
                } else {
                    Log.d("profile", response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.d("profile", t.toString());
            }
        });

        Call<List<Rank>> listCall = mAPIService.getRank("Bearer " + token, userId);
        listCall.enqueue(new Callback<List<Rank>>() {
            @Override
            public void onResponse(Call<List<Rank>> call, Response<List<Rank>> response) {
                if (response.code() == 200) {
                    profile_xp.setText(response.body().get(0).getXp());
                    profile_coin.setText(response.body().get(0).getGold());
                } else {
                    Log.d("rank", response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<List<Rank>> call, Throwable t) {
                Log.d("rank", t.toString());
            }
        });
    }
}
