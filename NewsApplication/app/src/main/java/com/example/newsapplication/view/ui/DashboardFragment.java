package com.example.newsapplication.view.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapplication.Helper.Constants;
import com.example.newsapplication.R;
import com.example.newsapplication.service.model.NewsData;
import com.example.newsapplication.view.adapter.NewsDataAdapter;
import com.example.newsapplication.view.adapter.NewsDataAdapter.OnNewsClickListener;
import com.example.newsapplication.viewModel.AppViewModelFactory;
import com.example.newsapplication.viewModel.NewsDataViewModel;

public class DashboardFragment extends Fragment implements OnNewsClickListener {

    RecyclerView mRecyclerView;
    NewsDataAdapter mNewsDataAdapter;

    public static DashboardFragment newInstance(String newsCategory){
        DashboardFragment dashboardFragment = new DashboardFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.NEWS_CATEGORY,newsCategory);
        dashboardFragment.setArguments(bundle);
        return dashboardFragment;
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        bindViews(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final String newsCategory = getArguments().getString(Constants.NEWS_CATEGORY);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        }
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mNewsDataAdapter = new NewsDataAdapter(getActivity(),this);
        mRecyclerView.setAdapter(mNewsDataAdapter);;
        getNewsData(newsCategory);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void getNewsData(String newsCategory) {
        NewsDataViewModel newsDataViewModel = ViewModelProviders.of(
                this,
                new AppViewModelFactory(getActivity().getApplication(),newsCategory))
                .get(NewsDataViewModel.class);
        newsDataViewModel.newsDataPageList.observe(this, new Observer<PagedList<NewsData>>() {
            @Override
            public void onChanged(PagedList<NewsData> newsData) {
                mNewsDataAdapter.submitList(newsData);
            }
        });
    }

    private void bindViews(View view) {
        mRecyclerView = view.findViewById(R.id.card_recycleView);
    }

    @Override
    public void onNewsClick(NewsData newsData) {
        //NewsData newsData = newsDataList.get(position);
        Intent intent = new Intent(getActivity(), DetailedNewsActivity.class);
        intent.putExtra(Constants.NEWS_DATA_OBJ,newsData);
        startActivity(intent);
    }
}
