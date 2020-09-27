package com.pranjal98.contagion2020;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("top-headlines")
    Call<News> getNews(

            @Query("country") String country,
            @Query("q") String q,
            @Query("apiKey") String apiKey
    );
}
