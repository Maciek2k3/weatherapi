package com.weather.api.repo;

import com.weather.api.domian.HotelGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelGroupRepo extends CrudRepository<HotelGroup, Long> {

    @Override
    <S extends HotelGroup> S save(S s);

    @Override
    List<HotelGroup> findAll();

    @Override
    void delete(HotelGroup hotelGroup);
}
