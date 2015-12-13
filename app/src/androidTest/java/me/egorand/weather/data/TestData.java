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

public final class TestData {

    public static final String MUNICH_WEATHER_DATA_JSON = "\n" +
            "{\n" +
            "    \"coord\": {\n" +
            "        \"lon\": 11.58,\n" +
            "        \"lat\": 48.14\n" +
            "    },\n" +
            "    \"weather\": [{\n" +
            "        \"id\": 741,\n" +
            "        \"main\": \"Fog\",\n" +
            "        \"description\": \"fog\",\n" +
            "        \"icon\": \"50n\"\n" +
            "    }],\n" +
            "    \"base\": \"cmc stations\",\n" +
            "    \"main\": {\n" +
            "        \"temp\": 275.68,\n" +
            "        \"pressure\": 1030,\n" +
            "        \"humidity\": 93,\n" +
            "        \"temp_min\": 274.15,\n" +
            "        \"temp_max\": 277.15\n" +
            "    },\n" +
            "    \"wind\": {\n" +
            "        \"speed\": 1.5,\n" +
            "        \"deg\": 240\n" +
            "    },\n" +
            "    \"clouds\": {\n" +
            "        \"all\": 0\n" +
            "    },\n" +
            "    \"dt\": 1449350400,\n" +
            "    \"sys\": {\n" +
            "        \"type\": 1,\n" +
            "        \"id\": 4887,\n" +
            "        \"message\": 0.0134,\n" +
            "        \"country\": \"DE\",\n" +
            "        \"sunrise\": 1449298092,\n" +
            "        \"sunset\": 1449328836\n" +
            "    },\n" +
            "    \"id\": 6940463,\n" +
            "    \"name\": \"Altstadt\",\n" +
            "    \"cod\": 200\n" +
            "}";

    private TestData() {
        // no instances
    }
}
