package com.weather.api.controller;


import com.weather.api.domian.BookingItem;
import com.weather.api.domian.HotelGroup;
import com.weather.api.domian.dto.BookingItemDto;

import com.weather.api.mapper.BookingItemMapper;
import com.weather.api.repo.HotelGroupRepo;
import com.weather.api.service.BookingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bookingitem/v1")
@CrossOrigin(origins = "*")
public class BookingItemController {

    private BookingItemMapper bookingItemMapper;
    private BookingItemService bookingItemService;

    @Autowired
    private HotelGroupRepo hotelGroupRepo;

    public BookingItemController(BookingItemMapper bookingItemMapper, BookingItemService bookingItemService) {
        this.bookingItemMapper = bookingItemMapper;
        this.bookingItemService = bookingItemService;
    }

    @GetMapping("/getItems")
    public List<BookingItemDto> getAllBookingItem() {
        List<BookingItem> bookingItemList = bookingItemService.findAllBookings();
        return bookingItemMapper.mapToBookItem(bookingItemList);
    }

    @GetMapping("/getItem")
    @ResponseBody
    public BookingItemDto getByHotelItem(@RequestParam long id) throws NotFoundExeption {
        return bookingItemMapper.mapToBookingItemDto(bookingItemService.findBookingById(id).orElseThrow(NotFoundExeption::new));

    }

    @DeleteMapping("/deleteItem")
    public void deleteByIdItem(@RequestParam long id) {
        bookingItemService.deleteBooking(id);
    }

    @PutMapping("/updateItem")
    public BookingItemDto udpateBook(@RequestBody BookingItemDto bookingItemDto) {
        BookingItem bookingItem = bookingItemMapper.mapToBookingItem(bookingItemDto);
        BookingItem saveBookingItem = bookingItemService.saveBookingItem(bookingItem);
        return bookingItemMapper.mapToBookingItemDto(saveBookingItem);
    }

    @PostMapping("/addItem")
    public void addBookingItem(@RequestBody BookingItemDto bookingItemDto) {
        BookingItem bookingItem = bookingItemMapper.mapToBookingItem(bookingItemDto);
        bookingItemService.saveBookingItem(bookingItem);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void saveItems() {
        HotelGroup hotelGroup = new HotelGroup(1L, "1Star");
        hotelGroupRepo.save(hotelGroup);
    }
}


