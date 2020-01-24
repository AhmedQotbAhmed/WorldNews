package com.example.news.UI.main;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.news.fragment.BusinessFragment;
import com.example.news.fragment.HomeFragment;
import com.example.news.fragment.SportFragment;

public class FragmentAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;

    public FragmentAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        this.myContext = context;
        this.totalTabs = totalTabs;

    }



    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:

                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 0:

                SportFragment sportFragment = new SportFragment();
                return sportFragment;
            case 2:

                BusinessFragment businessFragment = new BusinessFragment();
                return businessFragment;
            default:
                return null;
        }
    }

    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }







}
