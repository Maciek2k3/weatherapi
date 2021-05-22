package com.weather.api.controller;

import com.weather.api.domian.HotelGroup;
import com.weather.api.domian.dto.HotelGroupDto;
import com.weather.api.mapper.HotelGroupMapper;
import com.weather.api.service.HotelGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hotelgroup/v1")
@CrossOrigin(origins = "*")
public class HotelGroupController {
    private HotelGroupMapper hotelGroupMapper;
    private HotelGroupService hotelGroupService;

    @Autowired
    public HotelGroupController(HotelGroupMapper hotelGroupMapper, HotelGroupService hotelGroupService) {
        this.hotelGroupMapper = hotelGroupMapper;
        this.hotelGroupService = hotelGroupService;
    }

        @GetMapping("/getHotels")
        public List<HotelGroupDto> getAllHotelGroups() {
            List<HotelGroup> hotels = hotelGroupService.findAllHotels();
            return hotelGroupMapper.mapToHotelGroup(hotels);
        }

        @GetMapping("/getHotel")
        @ResponseBody
        public HotelGroupDto getByHotelGroup(@RequestParam long id) throws NotFoundExeption {
            return hotelGroupMapper.mapToHotelGroupDto(hotelGroupService.findHotelGroupById(id).orElseThrow(NotFoundExeption::new));

        }


        @DeleteMapping("/deleteHotel")
        public void deleteByIdHtelGroup(@RequestParam long id) {
            hotelGroupService.deleteHotelGroup(id);
        }

        @PutMapping("/updateHotel")
        public HotelGroupDto udpateBook(@RequestBody HotelGroupDto hotelGroupDto) {
        HotelGroup hotelGroup= hotelGroupMapper.mapToHotelGroup(hotelGroupDto);
        HotelGroup saveHotelGroup=hotelGroupService.saveHotelGroupById(hotelGroup);
        return hotelGroupMapper.mapToHotelGroupDto(saveHotelGroup);
        }

        @PostMapping("/addHotelGroup")
        public void addHotelGroup(@RequestBody HotelGroupDto hotelGroupDto) {
        HotelGroup hotelGroup=hotelGroupMapper.mapToHotelGroup(hotelGroupDto);
        hotelGroupService.saveHotelGroupById(hotelGroup);
        }
    }


