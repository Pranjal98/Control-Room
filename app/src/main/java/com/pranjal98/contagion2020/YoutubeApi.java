package com.pranjal98.contagion2020;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YoutubeApi {

    @GET("search")
    Call<YoutubeRetrofit> getVideos(
            @Query("part") String part,
            @Query("q") String q,
            @Query("key") String key,
            @Query("type") String type
    );
}
