package com.pranjal98.contagion2020;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class District {

    private String state;

    @SerializedName("districtData")
    @Expose
    private List<ItemRetrofit> districtData = null;

    public String getState() {
        return state;
    }

    public List<ItemRetrofit> districtData() {
        return districtData;
    }
}
