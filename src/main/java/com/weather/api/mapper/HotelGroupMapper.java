package com.weather.api.mapper;

import com.weather.api.domian.HotelGroup;
import com.weather.api.domian.dto.HotelGroupDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelGroupMapper {


    public HotelGroup mapToHotelGroup(final HotelGroupDto hotelGroupDto) {
        return new HotelGroup(hotelGroupDto.getId(), hotelGroupDto.getName());
    }

    public HotelGroupDto mapToHotelGroupDto(final HotelGroup hotelGroup) {
        return new HotelGroupDto(hotelGroup.getId(), hotelGroup.getName());

    }

    public List<HotelGroupDto> mapToHotelGroup(final List<HotelGroup> hotelGroups) {
        return hotelGroups.stream().map(this::mapToHotelGroupDto).collect(Collectors.toList());
    }

    public List<HotelGroup> mapToHotelGroupDto(final List<HotelGroupDto> hotelGroupDtoList) {
        return hotelGroupDtoList.stream().map(this::mapToHotelGroup).collect(Collectors.toList());
    }

}

