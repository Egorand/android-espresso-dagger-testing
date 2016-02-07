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

package me.egorand.weather.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import me.egorand.weather.BuildConfig;
import me.egorand.weather.R;
import me.egorand.weather.rest.ApiKeyRequestInterceptor;
import me.egorand.weather.rest.WeatherApiClient;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

@Module
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides @AppScope public Context provideAppContext() {
        return context;
    }

    @Provides public WeatherApiClient provideWeatherApiClient() {
        return new RestAdapter.Builder()
                .setEndpoint(WeatherApiClient.ENDPOINT)
                .setRequestInterceptor(apiKeyRequestInterceptor())
                .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .build()
                .create(WeatherApiClient.class);
    }

    private RequestInterceptor apiKeyRequestInterceptor() {
        return new ApiKeyRequestInterceptor(context.getString(R.string.open_weather_api_key));
    }
}
