package com.example.newsapplication.service.DataRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.newsapplication.service.model.NewsData;

public class NewsDataSourceFactory extends DataSource.Factory<Integer, NewsData> {
    private MutableLiveData<PageKeyedDataSource<Integer,NewsData>> itemLiveDataSource = new MutableLiveData<>();
    String newsCategory;

    public NewsDataSourceFactory(String newsCategory){
        this.newsCategory = newsCategory;
    }

    @Override
    public DataSource<Integer, NewsData> create() {
        ItemKeyedNewsDataSource itemKeyedNewsDataSource = new ItemKeyedNewsDataSource(newsCategory);
        itemLiveDataSource.postValue(itemKeyedNewsDataSource);
        return itemKeyedNewsDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer,NewsData>> getItemLiveDataSource(){
        return itemLiveDataSource;
    }
}
