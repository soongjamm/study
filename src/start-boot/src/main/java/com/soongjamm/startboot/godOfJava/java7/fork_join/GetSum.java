package com.soongjamm.startboot.godOfJava.java7.fork_join;

import lombok.SneakyThrows;

import java.util.concurrent.RecursiveTask;

public class GetSum extends RecursiveTask<Long> {

    long from, to;

    public GetSum(long from, long to) {
        this.from = from;
        this.to = to;
    }

    @SneakyThrows
    @Override
    protected Long compute() {
        long gap = to - from;

        Thread.sleep(1000);

        System.out.println();
        log("From=" + from + " To=" + to);
        if (gap <= 3) {
            long tempSum = 0;
            for (long loop = from; loop <= to; loop++) {
                tempSum += loop;
            }
            log("Return !! " + from + " ~ " + to + " = " + tempSum);
            return tempSum;
        }

        long middle = (from + to) / 2;
        GetSum sumPre = new GetSum(from, middle);
        log("Pre    From=" + from + " To=" + middle);
        sumPre.fork();
        GetSum sumPost = new GetSum(middle + 1, to);
        log("Post    From=" + (middle + 1) + " To=" + to);
        return sumPost.compute() + sumPre.join();
    }


    public void log(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.println("[" + threadName + "] " + message);
    }

}
