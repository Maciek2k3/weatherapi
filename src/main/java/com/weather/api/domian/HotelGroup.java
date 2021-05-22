package com.weather.api.domian;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "hotelGroup")
public class HotelGroup {

    @Id
    private Long id;
    @Column(name = "name")
    private String name;


    public HotelGroup(Long hotelId) {
        id = hotelId;
    }

    @Override
    public String toString() {
        return name;
    }
}

