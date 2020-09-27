package com.pranjal98.contagion2020;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ResourcesApi {

    @GET("resources/resources.json")
    Call<Resource> getResource();
}
