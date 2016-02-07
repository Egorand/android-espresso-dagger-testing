/*
 * Copyright 2015 - 2016 Egor Andreevici
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package me.egorand.weather.ui.activities;

import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import javax.inject.Inject;

import me.egorand.weather.R;
import me.egorand.weather.WeatherApp;
import me.egorand.weather.databinding.ActivityMainBinding;
import me.egorand.weather.rest.WeatherApiClient;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final String LOGTAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;
    private MenuItem searchMenuItem;

    @Inject WeatherApiClient weatherApiClient;

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((WeatherApp) getApplication()).appComponent().inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        searchMenuItem = menu.findItem(R.id.action_search);
        tintSearchMenuItem();
        initSearchView();
        return true;
    }

    private void tintSearchMenuItem() {
        int color = ContextCompat.getColor(this, android.R.color.white);
        searchMenuItem.getIcon().setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }

    private void initSearchView() {
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (!TextUtils.isEmpty(query)) {
            MenuItemCompat.collapseActionView(searchMenuItem);
            loadWeatherData(query);
        }
        return true;
    }

    private void loadWeatherData(String cityName) {
        binding.progress.setVisibility(View.VISIBLE);
        subscription = weatherApiClient.getWeatherForCity(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        weatherData -> {
                            binding.progress.setVisibility(View.INVISIBLE);
                            binding.weatherLayout.setVisibility(View.VISIBLE);
                            binding.setWeatherData(weatherData);
                        },
                        throwable -> {
                            binding.progress.setVisibility(View.INVISIBLE);
                            Log.e(LOGTAG, throwable.getLocalizedMessage(), throwable);
                        }
                );
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    protected void onDestroy() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }
}
