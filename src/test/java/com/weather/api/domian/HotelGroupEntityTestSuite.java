package com.weather.api.domian;

import com.weather.api.repo.HotelGroupRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class HotelGroupEntityTestSuite {
    @Autowired
    HotelGroupRepo hotelGroupRepo;

    @Test
    public void testHotelDaoCreateReadSave() {
        //Given
        HotelGroup hotelGroup = new HotelGroup(1l, "1Star");
        //When
        hotelGroupRepo.save(hotelGroup);
        long id = hotelGroup.getId();
        Optional<HotelGroup> readHotelGroup = hotelGroupRepo.findById(id);
        //Then
        Assert.assertTrue(readHotelGroup.isPresent());

    }


    @Test
    public void testItemDaoCreateReadDelete() {
        //Given
        HotelGroup hotelGroup = new HotelGroup(1l, "1Star");
        //When
        hotelGroupRepo.save(hotelGroup);
        long id = hotelGroup.getId();
        hotelGroupRepo.deleteById(id);
        Optional<HotelGroup> hotelGroupRead = hotelGroupRepo.findById(id);
        //Then
        Assert.assertFalse(hotelGroupRead.isPresent());
    }

    @Test
    public void testItemUpdate() {
        //Given
        HotelGroup hotelGroup = new HotelGroup(1l, "1Star");
        //When
        hotelGroupRepo.save(hotelGroup);
        hotelGroup.setName("test");
        //Then
        assertEquals("test", hotelGroup.getName());

    }


}
