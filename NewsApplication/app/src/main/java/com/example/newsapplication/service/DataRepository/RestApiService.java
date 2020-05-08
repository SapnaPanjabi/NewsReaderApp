package com.example.newsapplication.service.DataRepository;

import com.example.newsapplication.service.model.NewsData;
import com.example.newsapplication.service.model.NewsDataList;
import com.example.newsapplication.service.model.OpenWeatherMap;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RestApiService {

    @GET("top-headlines?country=in&apiKey=<apiKey>")
    Call<NewsDataList> getNewDataService(@Query("category")String category, @Query("page") int page, @Query("pageSize")int pageSize);

    @GET("top-headlines?country=in&apiKey=<apiKey>")
    Call<NewsDataList> getNewDataService(@Query("page") int page, @Query("pageSize")int pageSize);

    @GET("weather?appid=<apiKey>")
    Call<OpenWeatherMap> getWeatherService(@Query("lat") float latitude, @Query("lon")float longitude);

}
