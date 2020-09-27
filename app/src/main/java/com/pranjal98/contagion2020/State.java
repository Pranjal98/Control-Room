package com.pranjal98.contagion2020;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.xml.transform.Source;

public class State {

    @SerializedName("statewise")
    @Expose
    private List<StateItemRetrofit> statewise = null;

    public List<StateItemRetrofit> getStatewise() {
        return statewise;
    }
}
