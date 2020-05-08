package com.example.newsapplication.service.model;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NewsData implements Serializable {
    @SerializedName("source")
    private NewsSourceData newsSourceData;
    @SerializedName("author")
    private String newsAuthor;
    @SerializedName("title")
    private String newsTitle;
    @SerializedName("description")
    private String newsDesc;
    @SerializedName("url")
    private String newsURL;
    @SerializedName("urlToImage")
    private String newsImgURL;
    @SerializedName("publishedAt")
    private String newsPublishedAt;
    @SerializedName("content")
    private String newsContent;

    public NewsSourceData getNewsSourceData() {
        return newsSourceData;
    }

    public String getNewsAuthor() {
        return newsAuthor;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public void setNewsAuthor(String newsAuthor) {
        this.newsAuthor = newsAuthor;
    }

    public String getNewsDesc() {
        return newsDesc;
    }

    public void setNewsDesc(String newsDesc) {
        this.newsDesc = newsDesc;
    }

    public String getNewsURL() {
        return newsURL;
    }

    public void setNewsURL(String newsURL) {
        this.newsURL = newsURL;
    }

    public String getNewsImgURL() {
        return newsImgURL;
    }

    public void setNewsImgURL(String newsImgURL) {
        this.newsImgURL = newsImgURL;
    }

    public String getNewsPublishedAt() {
        return newsPublishedAt;
    }

    public void setNewsPublishedAt(String newsPublishedAt) {
        this.newsPublishedAt = newsPublishedAt;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }
}

