package com.example.newsapplication.service.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Coord implements Serializable {
    @SerializedName("lat")
    private double lat;
    @SerializedName("lon")
    private double lon;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
