package com.example.newsapplication;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

public class NewsDataApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Configuration dbConfiguration = new Configuration.Builder(this).setDatabaseName("xxx.db").create();
        ActiveAndroid.initialize(this);
    }
}
