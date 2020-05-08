package com.example.newsapplication.service.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class NewsDataList implements Serializable{

    @SerializedName("status")
    private String status;
    @SerializedName("totalResults")
    private String totalResults;
    @SerializedName("articles")
    private List<NewsData> newsDataList;

    public String getStatus() {
        return status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public List<NewsData> getNewsDataList() {
        return newsDataList;
    }

    public void setNewsDataList(List<NewsData> newsDataList) {
        this.newsDataList = newsDataList;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }
}
