package com.weather.api.mapper;

import com.weather.api.domian.BookingItem;
import com.weather.api.domian.dto.BookingItemDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingItemMapper {


    public BookingItem mapToBookingItem(final BookingItemDto bookingItem) {
        return new BookingItem(bookingItem.getId(), bookingItem.getTitle(), bookingItem.getContent(), bookingItem.getPrice(), bookingItem.getHotelStars());
    }

    public BookingItemDto mapToBookingItemDto(final BookingItem bookingItem) {
        return new BookingItemDto(bookingItem.getId(), bookingItem.getTitle(), bookingItem.getContent(), bookingItem.getPrice(), bookingItem.getHotelGroup());

    }

    public List<BookingItemDto> mapToBookItem(final List<BookingItem> bookingItemList) {
        return bookingItemList.stream().map(this::mapToBookingItemDto).collect(Collectors.toList());
    }

    public List<BookingItem> mapToBookItemDto(final List<BookingItemDto> bookingItemDtoList) {
        return bookingItemDtoList.stream().map(this::mapToBookingItem).collect(Collectors.toList());
    }

}
