package com.example.news.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    //انواع الحاجات اللي بجيب بيها الداتا
    // retrofit or httpUrlConnection or volley

    private static Retrofit retrofit;
    public static NewsApi getService(){


        if (retrofit==null){
            retrofit= new Retrofit
                    .Builder()
                    .baseUrl("https://newsapi.org/") //baseUrl
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit.create(NewsApi.class);



    }


}
