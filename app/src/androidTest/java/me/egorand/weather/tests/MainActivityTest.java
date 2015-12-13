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

package me.egorand.weather.tests;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.KeyEvent;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import me.egorand.weather.R;
import me.egorand.weather.TestWeatherApp;
import me.egorand.weather.data.WeatherData;
import me.egorand.weather.rest.WeatherApiClient;
import me.egorand.weather.ui.activities.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final String CITY_NAME = "München";

    @Rule public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Inject WeatherApiClient weatherApiClient;

    @Before
    public void setUp() {
        ((TestWeatherApp) activityTestRule.getActivity().getApplication()).appComponent().inject(this);
    }

    @Test
    public void correctWeatherDataDisplayed() {
        WeatherData weatherData = weatherApiClient.getWeatherForCity(CITY_NAME).toBlocking().first();

        onView(withId(R.id.action_search)).perform(click());
        onView(withId(android.support.v7.appcompat.R.id.search_src_text)).perform(replaceText(CITY_NAME));
        onView(withId(android.support.v7.appcompat.R.id.search_src_text)).perform(pressKey(KeyEvent.KEYCODE_ENTER));

        onView(withId(R.id.city_name)).check(matches(withText(weatherData.getCityName())));
        onView(withId(R.id.weather_date)).check(matches(withText(weatherData.getWeatherDate())));
        onView(withId(R.id.weather_state)).check(matches(withText(weatherData.getWeatherState())));
        onView(withId(R.id.weather_description)).check(matches(withText(weatherData.getWeatherDescription())));
        onView(withId(R.id.temperature)).check(matches(withText(weatherData.getTemperatureCelsius())));
        onView(withId(R.id.humidity)).check(matches(withText(weatherData.getHumidity())));
    }
}
