package com.weather.api.domian;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "BookingItem")
public class BookingItem {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "price")
    private double price;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "hotel_id")
    private HotelGroup hotelGroup;


    public BookingItem(Long id, String title, String content, double price, HotelGroup hotelGroup) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.price = price;
        this.hotelGroup = hotelGroup;
    }

}

