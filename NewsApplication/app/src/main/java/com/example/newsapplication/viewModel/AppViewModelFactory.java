package com.example.newsapplication.viewModel;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AppViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application mApplication;
    private Object[] mParams;

    public AppViewModelFactory(Application application, Object... params) {
        mApplication = application;
        mParams = params;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass == NewsDataViewModel.class) {
            return (T) new NewsDataViewModel((String) mParams[0]);
        } else {
            return super.create(modelClass);
        }
    }
}