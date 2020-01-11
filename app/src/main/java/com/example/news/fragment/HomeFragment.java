package com.example.news.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.UI.main.NewsAdapter;
import com.example.news.data.NewsApi;
import com.example.news.data.RetrofitClient;
import com.example.news.pojo.NewsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {


    RecyclerView recyclerView;
    private NewsAdapter newsAdapter;


    public HomeFragment( ) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        recyclerView = view.findViewById(R.id.recycler_home);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getNews();
        super.onViewCreated(view, savedInstanceState);

    }
    // run

    private void getNews() {
        NewsApi call = RetrofitClient.getService();
        call.getNews().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                newsAdapter = new NewsAdapter(response.body().getArticles());
                recyclerView.setAdapter(newsAdapter);

            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {

            }
        });

    }







}