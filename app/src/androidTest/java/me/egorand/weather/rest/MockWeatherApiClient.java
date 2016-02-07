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

package me.egorand.weather.rest;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import me.egorand.weather.data.TestData;
import me.egorand.weather.data.WeatherData;
import rx.Observable;

public class MockWeatherApiClient implements WeatherApiClient {

    @Override public Observable<WeatherData> getWeatherForCity(String cityName) {
        WeatherData weatherData = new Gson().fromJson(TestData.MUNICH_WEATHER_DATA_JSON, WeatherData.class);
        return Observable.just(weatherData).delay(1, TimeUnit.SECONDS);
    }
}
