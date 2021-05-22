package com.weather.api.domian;

import com.weather.api.repo.BookingItemRepo;
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
public class BookingItemEntityTestSuite {
    @Autowired
    BookingItemRepo bookingItemRepo;
    @Autowired
    HotelGroupRepo hotelGroupRepo;

    @Test
    public void testItempDaoCreateReadSave() {
        //Given
        BookingItem bookingItem = new BookingItem(1l,"Test","Test",2.0,new HotelGroup(1l,"1Star"));
        //When
        bookingItemRepo.save(bookingItem);
        long id = bookingItem.getId();
        Optional<BookingItem> readBookingItem = bookingItemRepo.findById(id);
        //Then
        Assert.assertTrue(readBookingItem.isPresent());

    }


    @Test
    public void testItemDaoCreateReadDelete() {
        //Given
        BookingItem bookingItem = new BookingItem(1l,"Test","Test",2.0,new HotelGroup(1l,"1Star"));
        //When
        bookingItemRepo.save(bookingItem);
        long id = bookingItem.getId();
        bookingItemRepo.deleteById(id);
        Optional<BookingItem> readBookingItem = bookingItemRepo.findById(id);
        //Then
        Assert.assertFalse(readBookingItem.isPresent());
    }

    @Test
    public void testItemUpdate() {
        //Given
        BookingItem bookingItem = new BookingItem(1l,"Test","Test",2.0,new HotelGroup(1l,"1Star"));
        //When
        bookingItemRepo.save(bookingItem);
        long id = bookingItem.getId();
        bookingItem.setContent("test content");
        //Then
        assertEquals("test content", bookingItem.getContent());

    }

    @Test
    public void testRelationsBetweenItemHotelGroupSave() {
        //Given
        HotelGroup hotelGroup = new HotelGroup(1L, "check");
        BookingItem bookingItem = new BookingItem(1l,"Test","Test",2.0,hotelGroup);
        //When
        bookingItem.setHotelGroup(hotelGroup);
        hotelGroupRepo.save(hotelGroup);
        bookingItemRepo.save(bookingItem);
        Long getIdfromHotelGroup = hotelGroup.getId();
        Long bookingItemId = bookingItem.getHotelGroup().getId();
        //Then
        Assert.assertNotEquals(Optional.of(0), getIdfromHotelGroup);
        Assert.assertEquals(getIdfromHotelGroup, bookingItemId);


    }
}