package com.weather.api.service;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.weather.api.domian.dto.WeatherDto;
import com.weather.api.mapper.WeatherMapper;
import lombok.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;


@RequiredArgsConstructor
@Service
public class WeatherService {

    private final static String API = "5134d3540bde9412329cdf970c824425";

    private final OkHttpClient client;
    private final WeatherMapper weatherMapper;

    public WeatherDto getWeather(String city, String unit) throws JSONException {
        final JSONObject jsonObject = sendRequest(city, unit);
        return weatherMapper.map(jsonObject);
    }

    private JSONObject sendRequest(String city, String unit) {
        Request request = new Request.Builder().
                url("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=" + unit + "&appid=5134d3540bde9412329cdf970c824425")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
