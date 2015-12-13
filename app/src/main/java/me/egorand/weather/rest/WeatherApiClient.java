/*
 * Copyright 2015 Egor Andreevici
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

import me.egorand.weather.data.WeatherData;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface WeatherApiClient {

    Endpoint ENDPOINT = Endpoints.newFixedEndpoint("http://api.openweathermap.org/data/2.5");

    @GET("/weather") Observable<WeatherData> getWeatherForCity(@Query("q") String cityName);
}
