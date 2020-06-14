package com.example.news.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.UI.main.Login;
import com.example.news.UI.main.NewsAdapter;
import com.example.news.data.NewsApi;
import com.example.news.data.RetrofitClient;
import com.example.news.pojo.NewsResponse;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessFragment extends Fragment {
    private FirebaseAuth firebaseAuth;
    RecyclerView recyclerView;
    private  NewsAdapter newsAdapter;

    public BusinessFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_business, container, false);
        firebaseAuth= FirebaseAuth.getInstance();
        recyclerView = view.findViewById(R.id.recycler_business);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        getNews();
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {



            case R.id.signOut_mn:
                FirebaseAuth.getInstance().signOut();
                getActivity().finish();
                Intent intent=new Intent(getActivity(), Login.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getNews() {
        NewsApi call = RetrofitClient.getService();
        // call function return dataClass
        call.getNews("US",
                "business",
                "211e7a892d3849c5a9b4f09704ae46d8")
                .enqueue(new Callback<NewsResponse>() {

            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                newsAdapter = new NewsAdapter(response.body().getArticles());
                recyclerView.setAdapter(newsAdapter);


            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {

                Log.e("error",t.getMessage());

            }
        });

    }




}


