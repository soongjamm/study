package com.soongjamm.startboot.godOfJava.formatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

public class DateFormatSample {
    public static void main(String[] args) {
        LocalDate from = LocalDate.of(2014, Month.JANUARY, 19);
        LocalDate now = LocalDate.now();

        from.datesUntil(now).forEach(x -> System.out.println(x.toString()));
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
//        simpleDateFormat.applyPattern("");
    }
}
