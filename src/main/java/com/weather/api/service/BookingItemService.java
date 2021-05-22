package com.weather.api.service;

import com.weather.api.domian.BookingItem;
import com.weather.api.repo.BookingItemRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class BookingItemService {

    @Autowired
    BookingItemRepo bookingItemRepo;

    public List<BookingItem> findAllBookings() {
        return bookingItemRepo.findAll();

    }

    public void deleteBooking(Long id) {
        bookingItemRepo.deleteById(id);
    }

    public Optional<BookingItem> findBookingById(Long id) {
        return bookingItemRepo.findById(id);
    }

    public BookingItem saveBookingItem(final BookingItem bookingItem) {
        return bookingItemRepo.save(bookingItem);
    }

}


