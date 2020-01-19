package com.example.patriotbumiandroid.adapter.ViewPager;

import com.example.patriotbumiandroid.activity.Rank.AllRankFragment;
import com.example.patriotbumiandroid.activity.Rank.MonthRankFragment;
import com.example.patriotbumiandroid.activity.Rank.WeekRankFragment;
import com.example.patriotbumiandroid.model.Rank;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class RankViewPagerAdapter extends FragmentStatePagerAdapter {

    private Fragment[] childFragment;

    public RankViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        childFragment = new Fragment[] {
                new AllRankFragment(),
                new MonthRankFragment(),
                new WeekRankFragment()
        };
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return childFragment[position];
    }

    @Override
    public int getCount() {
        return childFragment.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "All";
            case 1:
                return "Month";
            case 2:
                return "Week";
        }
        return null;
    }
}