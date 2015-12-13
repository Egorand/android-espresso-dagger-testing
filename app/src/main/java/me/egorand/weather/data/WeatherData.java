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

package me.egorand.weather.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeatherData {

    public static final String DATE_FORMAT = "EEEE, d MMM";

    private static final int KELVIN_ZERO = 273;

    private static final String FORMAT_TEMPERATURE_CELSIUS = "%d°";
    private static final String FORMAT_HUMIDITY = "%d%%";

    private String name;
    private Weather[] weather;
    private Main main;

    public String getCityName() {
        return name;
    }

    public String getWeatherDate() {
        return new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(new Date());
    }

    public String getWeatherState() {
        return weather().main;
    }

    public String getWeatherDescription() {
        return weather().description;
    }

    public String getTemperatureCelsius() {
        return String.format(FORMAT_TEMPERATURE_CELSIUS, (int) main.temp - KELVIN_ZERO);
    }

    public String getHumidity() {
        return String.format(FORMAT_HUMIDITY, main.humidity);
    }

    private Weather weather() {
        return weather[0];
    }

    private static class Weather {
        private String main;
        private String description;
    }

    private static class Main {
        private float temp;
        private int humidity;
    }
}
