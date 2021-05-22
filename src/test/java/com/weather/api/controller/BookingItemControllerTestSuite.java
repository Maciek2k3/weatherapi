package com.weather.api.controller;

import com.google.gson.Gson;
import com.weather.api.domian.BookingItem;
import com.weather.api.domian.HotelGroup;
import com.weather.api.domian.dto.BookingItemDto;
import com.weather.api.mapper.BookingItemMapper;
import com.weather.api.service.BookingItemService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringJUnitWebConfig
@AutoConfigureMockMvc
@SpringBootTest
public class BookingItemControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingItemService bookingItemService;

    @MockBean
    private BookingItemMapper bookingItemMapper;

    @Test
    public void shouldFetchEmptyTaskList() throws Exception {
        //Given
        List<BookingItem> bookingItemList = new ArrayList<>();
        given(bookingItemService.findAllBookings()).willReturn(bookingItemList);
        //When & Then
        mockMvc.perform(get("/bookingitem/v1/getItems").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)));
    }


    @Test
    public void shouldFetchBookingItems() throws Exception {
        //Given
        HotelGroup hotelGroup = new HotelGroup(1L, "5star");
        BookingItem bookingItem=new BookingItem(1L, "new", "new", 3, hotelGroup);
        List<BookingItem> bookingItems=Arrays.asList(bookingItem);
        BookingItemDto bookingItemDto=new BookingItemDto(1L, "new", "new", 3, hotelGroup);
        List<BookingItemDto> bookingItemDtoList = Arrays.asList(bookingItemDto);
        when(bookingItemService.findAllBookings()).thenReturn(bookingItems);
        when(bookingItemMapper.mapToBookItem(bookingItems)).thenReturn(bookingItemDtoList);
        //When&Then
        mockMvc.perform(MockMvcRequestBuilders.get("/bookingitem/v1/getItems")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("new")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("new")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price", Matchers.is(3.0)));
    }

    @Test
    public void shouldFetchBookingItem() throws Exception {
        //Given

        HotelGroup hotelGroup = new HotelGroup(1L, "5star");
        BookingItem bookingItem=new BookingItem(1L, "new", "new", 3, hotelGroup);
        BookingItemDto bookingItemDto=new BookingItemDto(1L, "new", "new", 3, hotelGroup);

        when(bookingItemMapper.mapToBookingItemDto(any(BookingItem.class))).thenReturn(bookingItemDto);
        when(bookingItemService.findBookingById(1L)).thenReturn(Optional.of(bookingItem));
        //When & Then
        String param="1";
        mockMvc.perform(get("/bookingitem/v1/getItem/")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("id", param))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("new")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("new")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price", Matchers.is(3.0)));
    }

    @Test
    public void shouldDeleteBookingItemWhichDoesNotExist() throws Exception {
        //Given
        when(bookingItemService.findBookingById(1L)).thenReturn(Optional.empty());
        //When & Then
        mockMvc.perform(delete("/bookingitem/v1/getItem/1").contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("id", "1"))
                .andExpect(status().is(404));
    }

    @Test
    public void shouldDeleteBookingItem() throws Exception {
        //Given
        HotelGroup hotelGroup = new HotelGroup(1L, "5star");
        BookingItem bookingItem=new BookingItem(1L, "new", "new", 3, hotelGroup);
        when(bookingItemService.findBookingById(1L)).thenReturn(Optional.of(bookingItem));
        //When & Then
        mockMvc.perform(delete("/bookingitem/v1/deleteItem")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("id", "1"))
                .andExpect(status().is(200));
    }


    @Test
    public void shouldUpdateBookingItem() throws Exception {
        //Given
        HotelGroup hotelGroup = new HotelGroup(1L, "5star");
        BookingItem bookingItem=new BookingItem(1L, "new", "new", 3, hotelGroup);
        BookingItemDto bookingItemDto=new BookingItemDto(1L, "new", "new", 3, hotelGroup);

        when(bookingItemMapper.mapToBookingItemDto(any(BookingItem.class))).thenReturn(bookingItemDto);
        when(bookingItemService.saveBookingItem(any(BookingItem.class))).thenReturn(bookingItem);
        when(bookingItemMapper.mapToBookingItem(any(BookingItemDto.class))).thenReturn(bookingItem);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookingItemDto);

        //When & Then
        mockMvc.perform(put("/bookingitem/v1/updateItem")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("new")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("new")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price", Matchers.is(3.0)));
    }

    @Test
    public void shouldAddBookingItem() throws Exception {
        HotelGroup hotelGroup = new HotelGroup(1L, "5star");
        BookingItem bookingItem=new BookingItem(1L, "new", "new", 3, hotelGroup);
        BookingItemDto bookingItemDto=new BookingItemDto(1L, "new", "new", 3, hotelGroup);

        when(bookingItemService.saveBookingItem(bookingItem)).thenReturn(bookingItem);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookingItemDto);

        mockMvc.perform(post("/bookingitem/v1/addItem").contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
    }
}


