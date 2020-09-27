package com.pranjal98.contagion2020;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StateApi {

    @GET("data.json")
    Call<State> getStates();
}
