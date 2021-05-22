package com.weather.api.mapper;

import com.weather.api.domian.dto.WeatherDto;
import com.weather.api.service.WeatherService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class WeatherMapper {

   // @Autowired
   // private WeatherService weatherService;

    public WeatherDto map(JSONObject jsonObject) throws JSONException {
        JSONObject main = jsonObject.getJSONObject("main");
        JSONObject wind = jsonObject.getJSONObject("wind");
        JSONArray weather = jsonObject.getJSONArray("weather");
        String iconCode = null;
        String weatherDescriptionNew = null;
        for (int i = 0; i < weather.length(); i++) {
            JSONObject weatherObj = weather.getJSONObject(i);
            iconCode = weatherObj.getString("icon");
            weatherDescriptionNew = weatherObj.getString("description");
        }

        return WeatherDto.builder()
                .temp(main.getDouble("temp"))
                .tempMax(main.getDouble("temp_max"))
                .tempMin(main.getDouble("temp_min"))
                .pressure(main.getDouble("pressure"))
                .humidity(main.getDouble("humidity"))
                .feelsLike(main.getDouble("feels_like"))
                .speed(wind.getDouble("speed"))
                .iconCode(iconCode)
                .weatherDescription(weatherDescriptionNew)
                .build();
    }
}



