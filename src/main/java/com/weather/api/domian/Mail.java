package com.weather.api.domian;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
@Builder
public class Mail {

    @NonNull
    private final String mailTo;

    private String toCc;

    @NonNull
    private final String subject;

    @NonNull
    private final String message;
}
