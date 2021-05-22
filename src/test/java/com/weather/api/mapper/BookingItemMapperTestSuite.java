package com.weather.api.mapper;

import com.weather.api.domian.BookingItem;
import com.weather.api.domian.HotelGroup;
import com.weather.api.domian.dto.BookingItemDto;
import com.weather.api.domian.dto.HotelGroupDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class BookingItemMapperTestSuite {
    @InjectMocks
    private BookingItemMapper bookingItemMapper;

    @Test
    public void mapToBookingItem() {
        //Given
        HotelGroup hotelGroup = new HotelGroup(1L, "test");
        BookingItem bookingItem = new BookingItem(1L, "title", "content", 3, hotelGroup);

        BookingItemDto bookingItemDto = new BookingItemDto(1L, "title", "content", 3, hotelGroup);
        //When
        BookingItem mappedBookingItem = bookingItemMapper.mapToBookingItem(bookingItemDto);
        //Then
        assertEquals(bookingItem.getId(), mappedBookingItem.getId());
        assertEquals(bookingItem.getTitle(), mappedBookingItem.getTitle());
        assertEquals(bookingItem.getContent(), mappedBookingItem.getContent());
        assertEquals(bookingItem.getPrice(), mappedBookingItem.getPrice(), 0.01);
        assertEquals(bookingItem.getHotelGroup(), mappedBookingItem.getHotelGroup());
    }

    @Test
    public void mapToItemDto() {
        //Given
        HotelGroup hotelGroup = new HotelGroup(1L, "test");
        BookingItem bookingItem = new BookingItem(1L, "title", "content", 3, hotelGroup);

        BookingItemDto bookingItemDto = new BookingItemDto(1L, "title", "content", 3, hotelGroup);
        //When
        BookingItemDto mappedbookingItemDto = bookingItemMapper.mapToBookingItemDto(bookingItem);
        //Then
        assertEquals(bookingItemDto.getId(), mappedbookingItemDto.getId());
        assertEquals(bookingItemDto.getTitle(), mappedbookingItemDto.getTitle());
        assertEquals(bookingItemDto.getContent(), mappedbookingItemDto.getContent());
        assertEquals(bookingItemDto.getPrice(), mappedbookingItemDto.getPrice(), 0.01);
        assertEquals(bookingItemDto.getHotelStars(), mappedbookingItemDto.getHotelStars());
    }

    @Test
    public void mapItemDtoList() {
        //Given
        HotelGroup hotelGroup = new HotelGroup(1L, "test");
        BookingItemDto bookingItemDto = new BookingItemDto(1L, "title", "content", 3, hotelGroup);
        List<BookingItemDto> bookingItemDtoList = Arrays.asList(bookingItemDto);

        BookingItem bookingItem = new BookingItem(1L, "title", "content", 3, hotelGroup);
        List<BookingItem> bookingItemList = Arrays.asList(bookingItem);

        //When
        List<BookingItemDto> bookingItemDtos = bookingItemMapper.mapToBookItem(bookingItemList);

        //Then
        assertNotNull(bookingItemDtos);
        assertEquals(1, bookingItemDtos.size());

        bookingItemDtos.forEach(t -> {
            assertEquals(bookingItemDto.getId(), t.getId());
            assertEquals(bookingItemDto.getTitle(), t.getTitle());
            assertEquals(bookingItemDto.getContent(), t.getContent());
            assertEquals(bookingItemDto.getPrice(), t.getPrice(), 0.01);
            assertEquals(bookingItemDto.getHotelStars(), t.getHotelStars());
        });

    }
    @Test
    public void mapItemList() {
        //Given
        HotelGroup hotelGroup = new HotelGroup(1L, "test");
        BookingItemDto bookingItemDto = new BookingItemDto(1L, "title", "content", 3, hotelGroup);
        List<BookingItemDto> bookingItemDtoList = Arrays.asList(bookingItemDto);

        BookingItem bookingItem = new BookingItem(1L, "title", "content", 3, hotelGroup);
        List<BookingItem> bookingItemList = Arrays.asList(bookingItem);

        //When
        List<BookingItem> bookingItemList1=bookingItemMapper.mapToBookItemDto(bookingItemDtoList);

        //Then
        assertNotNull(bookingItemList1);
        assertEquals(1, bookingItemList1.size());

        bookingItemList1.forEach(t -> {
            assertEquals(bookingItem.getId(), t.getId());
            assertEquals(bookingItem.getTitle(), t.getTitle());
            assertEquals(bookingItem.getContent(), t.getContent());
            assertEquals(bookingItem.getPrice(), t.getPrice(), 0.01);
            assertEquals(bookingItem.getHotelGroup(), t.getHotelGroup());
        });

    }
}
