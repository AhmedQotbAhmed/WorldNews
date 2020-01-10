package com.example.news.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.UI.main.NewsAdapter;

public class BusinessFragment extends Fragment {

    RecyclerView recyclerView;
    private final NewsAdapter newsAdapter;

    public BusinessFragment(NewsAdapter newsAdapter) {
        this.newsAdapter=newsAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_business, container, false);

        recyclerView = view.findViewById(R.id.recycler);


        recyclerView.setAdapter(newsAdapter);
        return view;
    }

}