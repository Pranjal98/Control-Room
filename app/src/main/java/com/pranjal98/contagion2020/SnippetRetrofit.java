package com.pranjal98.contagion2020;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SnippetRetrofit {
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("channelTitle")
    @Expose
    private String channelTitle;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }
}
