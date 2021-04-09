package com.soongjamm.startboot.practice.thread.completableFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceSample {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.submit(() -> runnable("a - "));
        service.submit(() -> runnable("b - "));
        service.submit(() -> runnable("c - "));
        service.submit(() -> runnable("d - "));
        service.submit(() -> runnable("e - "));
        service.shutdown();
    }

    private static void runnable(String s) {
        System.out.println(s + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
