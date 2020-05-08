package com.example.newsapplication.view.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.newsapplication.Helper.Constants;
import com.example.newsapplication.R;
import com.example.newsapplication.service.model.NewsData;
import com.example.newsapplication.service.model.NewsDataModel;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailedNewsActivity extends AppCompatActivity {

    ImageView imgNews;
    TextView tvTitle, tvDescription, tvContent, tvNewsOrigin;
    NewsData newsData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_news);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        newsData = (NewsData) getIntent().getSerializableExtra(Constants.NEWS_DATA_OBJ);
        bindViews();
        tvTitle.setText(newsData.getNewsTitle());
        tvDescription.setText(newsData.getNewsDesc());
        tvContent.setText(newsData.getNewsContent());
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(newsData.getNewsPublishedAt());
            String newsOrigin = newsData.getNewsAuthor() != null ?  new SimpleDateFormat("dd MMM yyyy, hh:mm a").format(date) + " | " + newsData.getNewsAuthor() : newsData.getNewsPublishedAt();
            tvNewsOrigin.setText(newsOrigin);
        }catch (Exception ex){}


        Glide.with(this)
                .load(newsData.getNewsImgURL())
                //.load("https://static-news.moneycontrol.com/static-mcnews/2019/06/RESERVE-bank-of-India-770x433.jpg")
                .apply(new RequestOptions().override(imgNews.getWidth(), 200).placeholder(R.drawable.ic_file_download_black_24dp).fitCenter())
                .into(imgNews);

    }

    private void bindViews() {
        tvTitle = findViewById(R.id.tvTitle);
        tvDescription = findViewById(R.id.tvDescription);
        tvContent =findViewById(R.id.tvContent);
        tvNewsOrigin = findViewById(R.id.tvNewsOrigin);
        imgNews = findViewById(R.id.img_news);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
               finish();
               return true;
            case R.id.action_bookmark:
                bookmarkPage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void bookmarkPage() {
        LinearLayout llDetailedNewsActivity = findViewById(R.id.ll_detailed_news_activity);
        if(NewsDataModel.isNewsExists(newsData)){
            Snackbar.make(llDetailedNewsActivity,getResources().getString(R.string.page_already_bookmarked), Snackbar.LENGTH_SHORT).show();
        }else {
            boolean status = NewsDataModel.saveDataInDB(newsData);
            Snackbar.make(llDetailedNewsActivity,getResources().getString(R.string.page_bookmarked), Snackbar.LENGTH_SHORT).show();
        }

    }
}
