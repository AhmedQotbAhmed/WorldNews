package com.example.news.UI.main;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.news.fragment.BusinessFragment;
import com.example.news.fragment.HomeFragment;
import com.example.news.fragment.SportFragment;

public class FragmentAdapter extends FragmentPagerAdapter {
    NewsAdapter newsAdapter;
    private Context myContext;
    int totalTabs;

    public FragmentAdapter(Context context, FragmentManager fm, int totalTabs,NewsAdapter newsAdapter) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
        this.newsAdapter=newsAdapter;
    }



    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:

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
