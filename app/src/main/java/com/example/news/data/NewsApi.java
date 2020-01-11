package com.example.news.data;

import com.example.news.pojo.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApi {
    //inter face

    @GET("v2/top-headlines?country=eg&category=technology&apiKey=c4652d58322344a783a6cea9e37e0707")
    Call<NewsResponse> getNews();
}
