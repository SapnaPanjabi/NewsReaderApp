package com.example.newsapplication.service.DataRepository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.newsapplication.Helper.Constants;
import com.example.newsapplication.service.model.NewsData;
import com.example.newsapplication.service.model.NewsDataList;
import com.example.newsapplication.service.model.OpenWeatherMap;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherDataRepository {
    private MutableLiveData<OpenWeatherMap> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<OpenWeatherMap> getMutableLiveData(final float latitude,final float longitude) {
        RestApiService restApiService = WeatherApi.getInstance().getWeatherApi();
        Call<OpenWeatherMap> call = restApiService.getWeatherService(latitude,longitude);
        call.enqueue(new Callback<OpenWeatherMap>() {
            @Override
            public void onResponse(Call<OpenWeatherMap> call, Response<OpenWeatherMap> response) {
                OpenWeatherMap openWeatherMap = response.body();
                if (openWeatherMap != null) {
                    mutableLiveData.setValue(openWeatherMap);
                }
            }

            @Override
            public void onFailure(Call<OpenWeatherMap> call, Throwable t) { }
        });
        return mutableLiveData;
    }

}
