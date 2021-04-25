package com.weather.api.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class WeatherService {
    private OkHttpClient client;
    private Response response;
    private String CityName;
    String unit;
    private String API = "5134d3540bde9412329cdf970c824425";

    public JSONObject getWeather() {
        client = new OkHttpClient();
        Request request = new Request.Builder().
                url("http://api.openweathermap.org/data/2.5/weather?q=" + getCityName() + "&units=" + getUnit() + "&appid=5134d3540bde9412329cdf970c824425")
                .build();
        try {
            response=client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray returnWeatherArray() throws JSONException {
        JSONArray waetherArray=getWeather().getJSONArray("weather");
        return waetherArray;
    }
    public JSONObject returnMain() throws JSONException {
        JSONObject main=getWeather().getJSONObject("main");
        return main;
    }

    public JSONObject returnWind() throws JSONException {
        JSONObject wind=getWeather().getJSONObject("wind");
        return wind;
    }

    public JSONObject returnSys() throws JSONException {
        JSONObject sys=getWeather().getJSONObject("sys");
        return sys;
    }

}
