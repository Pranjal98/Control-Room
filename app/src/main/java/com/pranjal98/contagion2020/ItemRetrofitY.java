package com.pranjal98.contagion2020;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemRetrofitY {
    @SerializedName("id")
    @Expose
    private VideoRetrofit id;

    @SerializedName("snippet")
    @Expose
    private SnippetRetrofit snippet;

    public VideoRetrofit getId() {
        return id;
    }

    public void setId(VideoRetrofit id) {
        this.id = id;
    }

    public SnippetRetrofit getSnippet() {
        return snippet;
    }

    public void setSnippet(SnippetRetrofit snippet) {
        this.snippet = snippet;
    }
}
