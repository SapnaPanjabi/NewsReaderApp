package com.example.newsapplication.service.DataRepository;

import com.example.newsapplication.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApi {
    public static WeatherApi mInstance;
    private static Retrofit retrofit = null;
    private WeatherApi() {
        if(retrofit==null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BuildConfig.BASE_URL_WEATHER_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    public static synchronized WeatherApi getInstance(){
        if (mInstance == null){
            mInstance = new WeatherApi();
        }
        return mInstance;
    }

    public RestApiService getWeatherApi(){
        return retrofit.create(RestApiService.class);
    }
}
