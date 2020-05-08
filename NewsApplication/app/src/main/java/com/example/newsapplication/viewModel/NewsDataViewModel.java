package com.example.newsapplication.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.newsapplication.service.DataRepository.ItemKeyedNewsDataSource;
import com.example.newsapplication.service.DataRepository.NewsDataSourceFactory;
import com.example.newsapplication.service.model.NewsData;

public class NewsDataViewModel extends ViewModel {

    public LiveData<PagedList<NewsData>> newsDataPageList;
    LiveData<PageKeyedDataSource<Integer,NewsData>> liveNewsDataSource;
    public NewsDataViewModel(String newsCategory)
    {
        //Getting news data source factory
        NewsDataSourceFactory newsDataSourceFactory =new NewsDataSourceFactory(newsCategory);

        //Getting livedata source from data source factory
        liveNewsDataSource = newsDataSourceFactory.getItemLiveDataSource();

        //Getting pagelist config
        PagedList.Config pagedListConfig=
                (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(ItemKeyedNewsDataSource.PAGE_SIZE).build();

        newsDataPageList =(new LivePagedListBuilder(newsDataSourceFactory,pagedListConfig)).build();
    }
}

