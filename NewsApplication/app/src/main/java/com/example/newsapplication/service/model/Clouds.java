package com.example.newsapplication.service.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Clouds implements Serializable {
    @SerializedName("all")
    private int all;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }
}
