package com.example.news.UI.main;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.news.data.NewsApi;
import com.example.news.data.RetrofitClient;
import com.example.news.fragment.BusinessFragment;
import com.example.news.fragment.HomeFragment;
import com.example.news.fragment.SportFragment;
import com.example.news.newsmodel.NewsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                getNews();
                HomeFragment homeFragment = new HomeFragment(newsAdapter);

                return homeFragment;
            case 1:
                getNews();
                SportFragment sportFragment = new SportFragment(newsAdapter);
                return sportFragment;
            case 2:
                getNews();
                BusinessFragment businessFragment = new BusinessFragment(newsAdapter);
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


    private void getNews() {
        NewsApi call = RetrofitClient.getService();
        call.getNews().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                 newsAdapter = new NewsAdapter(response.body().getArticles());

            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {

            }
        });

    }





}
