package com.weather.api.service;

import com.weather.api.domian.BookingItem;
import com.weather.api.domian.HotelGroup;
import com.weather.api.mapper.HotelGroupMapper;
import com.weather.api.repo.BookingItemRepo;
import com.weather.api.repo.HotelGroupRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class HotelGroupService {

    @Autowired
    HotelGroupRepo hotelGroupRepo;

    public List<HotelGroup> findAllHotels() {
        return hotelGroupRepo.findAll();

    }

    public void deleteHotelGroup(Long id) {
        hotelGroupRepo.deleteById(id);
    }

    public Optional<HotelGroup> findHotelGroupById(Long id) {
        return hotelGroupRepo.findById(id);
    }

    public HotelGroup saveHotelGroupById(final HotelGroup hotelGroup) {
        return hotelGroupRepo.save(hotelGroup);
    }

}


