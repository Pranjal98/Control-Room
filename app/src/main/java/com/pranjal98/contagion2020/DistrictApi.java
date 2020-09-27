package com.pranjal98.contagion2020;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DistrictApi {

    @GET("v2/state_district_wise.json")
    Call<List<District>> getDistricts();
}
