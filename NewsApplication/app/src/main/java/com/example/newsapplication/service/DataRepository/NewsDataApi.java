package com.example.newsapplication.service.DataRepository;

import com.example.newsapplication.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsDataApi {
    private static NewsDataApi mInstance;
    private static Retrofit retrofit = null;
    private NewsDataApi() {
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    public static synchronized NewsDataApi getInstance() {
        if (mInstance == null) {
            mInstance = new NewsDataApi();
        }
        return mInstance;
    }

    public RestApiService getNewsDataApi() {
        return retrofit.create(RestApiService.class);
    }
}
