package com.weather.api.repo;

import com.weather.api.domian.BookingItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingItemRepo extends CrudRepository<BookingItem, Long> {


    @Override
    Optional<BookingItem> findById(Long aLong);

    @Override
    List<BookingItem> findAll();

    @Override
    void delete(BookingItem bookingItem);
}
