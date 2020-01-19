package com.example.patriotbumiandroid.activity.PostAuthenticate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.patriotbumiandroid.R;
import com.example.patriotbumiandroid.activity.Activity.ActivityFragment;
import com.example.patriotbumiandroid.activity.Home.PostHomeFragment;
import com.example.patriotbumiandroid.activity.Profile.ProfileFragment;
import com.example.patriotbumiandroid.activity.Rank.RankFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PostMainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_main);

        loadFragment(new PostHomeFragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bn_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home_menu);
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
                fragment = new PostHomeFragment();
                break;
            case R.id.reward_menu:
                fragment = new RankFragment();
                break;
            case R.id.profil_menu:
                fragment = new ProfileFragment();
                break;
        }
        return loadFragment(fragment);
    }
}
