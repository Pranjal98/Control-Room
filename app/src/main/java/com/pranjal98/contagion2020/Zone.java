package com.pranjal98.contagion2020;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Zone {

    @SerializedName("zones")
    @Expose
    private List<ZoneRetrofit> zones = null;

    public List<ZoneRetrofit> getZones() {
        return zones;
    }
}
