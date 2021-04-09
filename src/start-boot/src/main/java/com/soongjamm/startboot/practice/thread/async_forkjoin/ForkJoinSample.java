package com.soongjamm.startboot.practice.thread.async_forkjoin;

import lombok.SneakyThrows;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class ForkJoinSample {
    static final ForkJoinPool mainPool = new ForkJoinPool();

    public static void main(String[] args) {
        ForkJoinSample sample = new ForkJoinSample();
        sample.calculate();
//        sample.recursiveAction();
    }

    @SneakyThrows
    private void recursiveAction() {

        MyRecursiveAction myRecursiveAction = new MyRecursiveAction(128);
        mainPool.invoke(myRecursiveAction);
        // Just wait until all tasks done
        mainPool.awaitTermination(5, TimeUnit.SECONDS);
    }


    @SneakyThrows
    private void calculate() {
        long from = 0;
        long to = 10;

        GetSum sum = new GetSum(from, to);
//        Future<Long> submit = mainPool.submit(sum);
//        Long result = submit.get();
        Long result = mainPool.invoke(sum);
        System.out.println("Fork Join : Total sum of " + from + " ~ " + to + " = " + result);
    }
}
