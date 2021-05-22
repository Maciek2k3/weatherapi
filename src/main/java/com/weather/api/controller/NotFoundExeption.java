package com.weather.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundExeption extends Exception {
    private static final String MESSAGE = "Item not found";

    public NotFoundExeption() {
        super(MESSAGE);
    }
}


