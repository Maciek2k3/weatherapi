package com.weather.api.domian;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue
    private Long id;
    private String imageAdress;
    @Column(name = "book_number", unique = true)
    private Long bookNumber;

    public Image(String imageAdress, Long bookNumber) {
        this.imageAdress = imageAdress;
        this.bookNumber = bookNumber;
    }


}
