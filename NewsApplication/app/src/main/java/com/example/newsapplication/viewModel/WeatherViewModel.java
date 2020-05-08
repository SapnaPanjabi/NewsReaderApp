package com.example.newsapplication.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.newsapplication.service.DataRepository.WeatherDataRepository;
import com.example.newsapplication.service.model.OpenWeatherMap;

public class WeatherViewModel extends AndroidViewModel {
    WeatherDataRepository weatherDataRepository;

    public WeatherViewModel(@NonNull Application application) {
        super(application);
        weatherDataRepository =new WeatherDataRepository();
    }

    public LiveData<OpenWeatherMap>getWeatherData(float latitude, float longitude){
        return weatherDataRepository.getMutableLiveData(latitude,longitude);
    }
}
