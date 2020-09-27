package com.pranjal98.contagion2020;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ZoneApi {

    @GET("zones.json")
    Call<Zone> getZones();
}
