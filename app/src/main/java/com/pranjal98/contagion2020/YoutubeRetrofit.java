package com.pranjal98.contagion2020;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YoutubeRetrofit {
    @SerializedName("items")
    @Expose
    private List<ItemRetrofitY> items = null;

    public List<ItemRetrofitY> getItems() {
        return items;
    }

    public void setItems(List<ItemRetrofitY> items) {
        this.items = items;
    }
}
