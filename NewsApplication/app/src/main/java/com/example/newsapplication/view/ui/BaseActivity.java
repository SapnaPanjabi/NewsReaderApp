package com.example.newsapplication.view.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapplication.R;
import com.example.newsapplication.service.model.NewsDataModel;
import com.example.newsapplication.service.model.OpenWeatherMap;
import com.example.newsapplication.viewModel.WeatherViewModel;
import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseActivity extends AppCompatActivity{
    private final static String TAG = BaseActivity.class.getName();
    private AppBarConfiguration mAppBarConfiguration;
    NavigationView navigationView;
    TextView tvCurrentLocation, tvCurrentWeather, tvCurrentTemp, tvMinTemp, tvMaxTemp, tvDateTime;
    WeatherViewModel weatherViewModel;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        bindHeaderUI();
        handleMenuClickEvents(navigationView);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_clear_bookmark)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    private void bindHeaderUI() {
        View header = navigationView.getHeaderView(0);
        tvCurrentLocation = header.findViewById(R.id.tvCurrentLocation);
        tvCurrentWeather = header.findViewById(R.id.tvCurrentWeather);
        tvCurrentTemp = header.findViewById(R.id.tvCurrentTemp);
        tvMinTemp = header.findViewById(R.id.tvMinTemp);
        tvMaxTemp = header.findViewById(R.id.tvMaxTemp);
        tvDateTime = header.findViewById(R.id.tvCurrentDateTime);
    }

    private void handleMenuClickEvents(NavigationView navigationView) {
        MenuItem mnu_clear= navigationView.getMenu().findItem(R.id.nav_clear_bookmark);
        mnu_clear.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                clearBookmarkPages();
                return true;
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        latitude = 12.9716;  //Will implement method to fetch latitude and longitude
        longitude = 77.5946;
        //latitude = 24.5854;
        //longitude = 77.5946;
        weatherViewModel.getWeatherData((float)latitude,(float)longitude).observe(this, new Observer<OpenWeatherMap>() {
            @Override
            public void onChanged(OpenWeatherMap openWeatherMap) {
                tvCurrentLocation.setText(openWeatherMap.getName());
                tvCurrentTemp.setText(""+Math.round(openWeatherMap.getMain().getTemp()-273.15)+"°");
                tvCurrentWeather.setText(openWeatherMap.getWeatherList().get(0).getDescription());
                tvMinTemp.setText(getResources().getString(R.string.min_temp) + Math.round(openWeatherMap.getMain().getTemp_min()-273.15)+" °C");
                tvMaxTemp.setText(getResources().getString(R.string.max_temp) + Math.round(openWeatherMap.getMain().getTemp_max()-273.15)+" °C");
                String currentDate = new SimpleDateFormat("EEE, dd MMM yyyy").format(new Date());
                tvDateTime.setText(currentDate);
            }
        });
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void clearBookmarkPages() {
        NewsDataModel.deleteAll();
        Toast.makeText(this,getResources().getString(R.string.bookmark_pages_deleted),Toast.LENGTH_SHORT).show();
    }

}
