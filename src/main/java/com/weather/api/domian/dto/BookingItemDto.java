package com.weather.api.domian.dto;

import com.weather.api.domian.HotelGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookingItemDto {

    private Long id;
    private String title;
    private String content;
    private double price;
    private HotelGroup hotelStars;

}
