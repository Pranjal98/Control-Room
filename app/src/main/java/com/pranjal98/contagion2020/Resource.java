package com.pranjal98.contagion2020;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Resource {

    @SerializedName("resources")
    @Expose
    private List<ResourceRetrofit> resources = null;

    public List<ResourceRetrofit> getResources() {
        return resources;
    }
}
