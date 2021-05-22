package com.weather.api.mapper;

import com.weather.api.domian.dto.WeatherDto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class WeatherMapperTestSuite {
/*
    @InjectMocks
    private WeatherMapper weatherMapper;

    @Test
    public void mapToWeatherlGroup() throws JSONException {
        //Given
        WeatherDto weatherDto = new WeatherDto(1, 1, 1, 1, 1, 1, 1, "new", "new");

        JSONObject jsonObject=new JSONObject();

        JSONArray jsonArray=new JSONArray();
        JSONObject icon=new JSONObject();
        JSONObject description=new JSONObject();
        description.put("description","clouds");

        icon.put("icon","new");
        jsonArray.put(icon);
        jsonArray.put(description);
        JSONObject main=new JSONObject();
        JSONObject temp=new JSONObject();
        temp.put("temp",2);
        jsonObject.put("weather",icon);

        JSONObject wind=new JSONObject();
        JSONObject speed=new JSONObject();
        speed.put("speed",1.2);
        wind.put("wind",speed);

        main.put("main",temp);
        System.out.println(wind);
        System.out.println(main);


        jsonObject.put("main",main);
        jsonObject.put("wind",wind);
        jsonObject.put("weather",jsonArray);

        System.out.println(jsonObject);
       // main.put(String.valueOf(icon),"new");
       // main.put(String.valueOf(temp),1);



      //  JSONObject wind = new JSONObject("wind");
      //  JSONArray weather = new JSONArray("weather");

        // main.put("temp_min", 1);
       // main.put("temp_Max", 1);
       // main.put("pressure", 1);
        //main.put("humidity", 1);
        //main.put("speed", 1);
       // main.put("feelsLike", 1);
       // main.put("speed",1);
       // main.put("weatherDescription","new");
        //When
        WeatherDto weathermapp = weatherMapper.map(jsonObject);
        //Then
        assertEquals(weatherDto.getTemp(), weathermapp.getTemp(), 0.001);
    }
}

  /*  JSONObject main = jsonObject.getJSONObject("main");
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
        }*/
}