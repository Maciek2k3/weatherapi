package com.weather.api.config;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@EnableScheduling
@Configuration
@Getter
public class CoreConfiguration {
}
