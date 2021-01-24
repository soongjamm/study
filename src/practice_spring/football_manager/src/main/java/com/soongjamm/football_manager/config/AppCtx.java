package com.soongjamm.football_manager.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Configuration
@ComponentScan(basePackages = {"com.soongjamm.football_manager.target"},
        excludeFilters = @Filter(type = FilterType.ASPECTJ, pattern = "com.soongjamm.football_manager.config"))
public class AppCtx {

    @Bean
    @Qualifier("localized-long")
    public DateTimeFormatter dateTimeFormatterLocalizedLong() {
        return DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
    }

    @Bean
    @Qualifier("localized-full")
    public DateTimeFormatter dateTimeFormatterLocalizedFull() {
        return DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
    }

}
