package com.soongjamm.startboot.practice.thread.completableFuture;

import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndFuture {
    @SneakyThrows
    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(4);
        String result =  service.invokeAny(createCallables());
        System.out.println("result = " + result);

        List<Future<String>> result2 =  service.invokeAll(createCallables());
        System.out.println("result2 : ---- ");
        for (Future<String> str : result2) {
            System.out.println("str = " + str.get());
        }
        service.shutdown();
    }

    private static List<Callable<String>> createCallables() {
        return Arrays.asList(
                () -> {
                    Thread.sleep(1000);
                    return "hi";
                },
                () -> {
                    Thread.sleep(2000);
                    return "bye TT";
                },
                () -> {
                    Thread.sleep(3000);
                    return "welcome again";
                }
        );
    }
}
