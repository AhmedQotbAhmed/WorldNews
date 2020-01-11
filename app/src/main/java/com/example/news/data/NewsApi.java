
package com.example.news.data;

        import com.example.news.pojo.NewsResponse;
        import retrofit2.Call;
        import retrofit2.http.GET;
        import retrofit2.http.Query;

public interface NewsApi {
    //1. make a interface

    //1- نوع الركوست بتاعي get


    //@GET("v2/top-headlines?country=eg&category=technology&apiKey=c4652d58322344a783a6cea9e37e0707")
    @GET("v2/top-headlines")

    // بنكلم بيها السيرفر و بيبقا ليها نوع اللي هو السيتر و الجيتار بتاع الpojo

    // call function return dataClass
                                    // serializedName
    Call<NewsResponse> getNews(@Query("country") String country,
                               @Query("category") String category,
                               @Query("apiKey") String key);

}

//              class      name
// Login(@Body LoginBody.varName)
// Class Loginbody
