package com.weather.api.domian.dto;

import lombok.*;

@Value
@Builder
@Getter
@AllArgsConstructor
public class WeatherDto {

    private double temp;
    private double tempMin;
    private double tempMax;
    private double pressure;
    private double humidity;
    private double speed;
    private double feelsLike;
    private String iconCode;
    private String weatherDescription;

}
