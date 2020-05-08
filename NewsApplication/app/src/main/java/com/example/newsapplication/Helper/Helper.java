package com.example.newsapplication.Helper;

import com.example.newsapplication.service.model.NewsDataList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Helper {
    public static NewsDataList JsonToObject(String jsonString){
        Gson gson = new Gson();
        // Converting json to object
        NewsDataList newsDataList = gson.fromJson(jsonString, NewsDataList.class);
        return newsDataList;
    }
}
