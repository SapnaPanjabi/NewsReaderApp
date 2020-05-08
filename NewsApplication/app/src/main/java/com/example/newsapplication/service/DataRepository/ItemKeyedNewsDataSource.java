package com.example.newsapplication.service.DataRepository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.newsapplication.Helper.Constants;
import com.example.newsapplication.service.model.NewsData;
import com.example.newsapplication.service.model.NewsDataList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemKeyedNewsDataSource extends PageKeyedDataSource<Integer,NewsData> {
    //size of a page
    public static final int PAGE_SIZE = 20;

    //start from the first page
    private static final int FIRST_PAGE = 1;
    private String newsCategory=null;

    public static final String TAG="ItemKeyedNewsDataSource";
    private MutableLiveData networkState;
    private MutableLiveData initialLoading;

    public ItemKeyedNewsDataSource(String newsCategory){
        this.newsCategory = newsCategory;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, NewsData> callback) {
        Log.i(TAG, "Loading : "+1+" Count "+params.requestedLoadSize);
        NewsDataApi.getInstance()
                .getNewsDataApi().getNewDataService(newsCategory,FIRST_PAGE,PAGE_SIZE)
                .enqueue(new Callback<NewsDataList>() {
                    @Override
                    public void onResponse(Call<NewsDataList> call, Response<NewsDataList> response) {
                        if(response.body()!=null){
                            callback.onResult(response.body().getNewsDataList(),null,FIRST_PAGE+1);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsDataList> call, Throwable t) {
                        Log.e("ItemKeyedNewsDataSource",t.getMessage());
                    }
                });

    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, NewsData> callback) {
            NewsDataApi.getInstance()
                    .getNewsDataApi().getNewDataService(newsCategory,params.key,PAGE_SIZE)
                    .enqueue(new Callback<NewsDataList>() {
                        @Override
                        public void onResponse(Call<NewsDataList> call, Response<NewsDataList> response) {
                            //If current page is >1 then only decrement the page
                            Integer previousPageKey = params.key> 1 ? params.key-1 : null;
                            if(response.body()!=null){
                                callback.onResult(response.body().getNewsDataList(),previousPageKey);
                            }
                        }

                        @Override
                        public void onFailure(Call<NewsDataList> call, Throwable t) {
                            Log.e("ItemKeyedNewsDataSource",t.getMessage());
                        }
                    });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, NewsData> callback) {
        NewsDataApi.getInstance()
                .getNewsDataApi().getNewDataService(newsCategory,params.key,PAGE_SIZE)
                .enqueue(new Callback<NewsDataList>() {
                    @Override
                    public void onResponse(Call<NewsDataList> call, Response<NewsDataList> response) {
                        if (response.body()!= null){
                            //If response has next page then increment the page
                            Integer nextPageKey = getPageCount(Integer.parseInt(response.body().getTotalResults())) >params.key
                                    ? params.key+1
                                    :null;

                            callback.onResult(response.body().getNewsDataList(),nextPageKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsDataList> call, Throwable t) {
                        Log.e("ItemKeyedNewsDataSource",t.getMessage());
                    }
                });
    }
    private int getPageCount(int totalResults)
    {
        int  pageCount = totalResults/ PAGE_SIZE;
        return (totalResults % PAGE_SIZE != 0) ? pageCount++ : pageCount;
    }
}
