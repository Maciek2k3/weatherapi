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
public class HotelGroupMpperTestSuite {
    @InjectMocks
    private HotelGroupMapper hotelGroupMapper;

    @Test
    public void mapToHotelGroup() {
        //Given
        HotelGroup hotelGroup = new HotelGroup(1L, "test");

        HotelGroupDto hotelGroupDto = new HotelGroupDto(1L, "test");
        //When
        HotelGroup mappedHotelGroup = hotelGroupMapper.mapToHotelGroup(hotelGroupDto);
        //Then
        assertEquals(hotelGroup.getId(), mappedHotelGroup.getId());
        assertEquals(hotelGroup.getName(), mappedHotelGroup.getName());
    }

    @Test
    public void mapToHotelGroupDto() {
        //Given
        HotelGroup hotelGroup = new HotelGroup(1L, "test");

        HotelGroupDto hotelGroupDto = new HotelGroupDto(1L, "test");
        //When
        HotelGroupDto mappedHotelGroupDto = hotelGroupMapper.mapToHotelGroupDto(hotelGroup);
        //Then
        assertEquals(hotelGroupDto.getId(), mappedHotelGroupDto.getId());
        assertEquals(hotelGroupDto.getName(), mappedHotelGroupDto.getName());
    }

    @Test
    public void mapHotelGroupDtoList() {
        //Given
        HotelGroup hotelGroup = new HotelGroup(1L, "test");
        List<HotelGroup> hotelGroups=Arrays.asList(hotelGroup);

        HotelGroupDto hotelGroupDto = new HotelGroupDto(1L, "test");
        List<HotelGroupDto> hotelGroupDtoList=Arrays.asList(hotelGroupDto);

        //When
        List<HotelGroupDto> hotelGroupDtoList1 = hotelGroupMapper.mapToHotelGroup(hotelGroups);

        //Then
        assertNotNull(hotelGroupDtoList1);
        assertEquals(1, hotelGroupDtoList1.size());

        hotelGroupDtoList1.forEach(t -> {
            assertEquals(hotelGroupDto.getId(), t.getId());
            assertEquals(hotelGroupDto.getName(), t.getName());
        });

    }
    @Test
    public void mapHotelGroupList() {
        //Given
        HotelGroup hotelGroup = new HotelGroup(1L, "test");
        List<HotelGroup> hotelGroups=Arrays.asList(hotelGroup);

        HotelGroupDto hotelGroupDto = new HotelGroupDto(1L, "test");
        List<HotelGroupDto> hotelGroupDtoList=Arrays.asList(hotelGroupDto);

        //When
        List<HotelGroup> hotelGroupList = hotelGroupMapper.mapToHotelGroupDto(hotelGroupDtoList);

        //Then
        assertNotNull(hotelGroupList);
        assertEquals(1, hotelGroupList.size());

        hotelGroupList.forEach(t -> {
            assertEquals(hotelGroup.getId(), t.getId());
            assertEquals(hotelGroup.getName(), t.getName());
        });


    }
}
