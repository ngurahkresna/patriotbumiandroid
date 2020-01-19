package com.example.patriotbumiandroid.activity.PreAuthenticate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.activity.Activity.ActivityFragment;
import com.example.patriotbumiandroid.activity.Home.PreHomeFragment;
import com.example.patriotbumiandroid.activity.Rank.RankFragment;
import com.example.patriotbumiandroid.activity.ToAuthenticate.ToAuthenticateFragment;
import com.example.patriotbumiandroid.data.APIService;
import com.example.patriotbumiandroid.data.APIUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new PreHomeFragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bn_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home_menu);

        mAPIService = APIUtils.getAPIService();
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
//            case R.id.cart_menu:
//                fragment = new ShopFragment();
//                break;
            case R.id.activity_menu:
                fragment = new ActivityFragment();
                break;
            case R.id.home_menu:
                fragment = new PreHomeFragment();
                break;
            case R.id.reward_menu:
                fragment = new RankFragment();
                break;
            case R.id.profil_menu:
                fragment = new ToAuthenticateFragment();
                break;
        }
        return loadFragment(fragment);
    }
}
