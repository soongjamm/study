package com.soongjamm.startboot.godOfJava.thread.practice;

import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeThread extends Thread {
    @Override
    public void run() {
        printCurrentTime();
    }

    @SneakyThrows
    public void printCurrentTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 a hh시 mm분 ss초");
        for (int i = 0; i < 10; i++) {
            System.out.println("=============================================");
            System.out.println(LocalDateTime.now().format(dateTimeFormatter));
            System.out.println(System.currentTimeMillis());
            Thread.sleep(900);
            reduceTimeGap();
        }
    }

    @SneakyThrows
    public void printCurrentTimeFirst() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 a hh시 mm분 ss초");
        for (int i = 0; i < 10; i++) {
            System.out.println("=============================================");
            System.out.println(LocalDateTime.now().format(dateTimeFormatter));
            System.out.println(System.currentTimeMillis());
            Thread.sleep(1000);
        }
    }


    public void reduceTimeGap() {
        long currentTime=System.currentTimeMillis();
        long timeMod=currentTime%1000;
        try {
            Thread.sleep(1000-timeMod);
        } catch (Exception e) {

        }
    }

    
}
