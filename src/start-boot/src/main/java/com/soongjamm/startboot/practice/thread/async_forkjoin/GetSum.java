package com.soongjamm.startboot.practice.thread.async_forkjoin;

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
        long gap = to-from;
        System.out.println("new-"+Thread.currentThread().getName()+ " " + from + " " + to );

        if(gap<=3) {
            long tempSum=0;
            for (long i = from; i <=to; i++) {
                tempSum += i;
            }
            System.out.println(tempSum);
            return tempSum;
        }

        long middle=(from+to)/2;
        GetSum sumPre = new GetSum(from, middle);
        System.out.println("middle-"+Thread.currentThread().getName()+ " " + from + " " + middle );
        sumPre.fork();
        System.out.println("sumpre called");
        GetSum sumPost = new GetSum(middle+1, to);
        System.out.println("bottom-"+Thread.currentThread().getName()+ " " + (middle+1) + " " + to );
        sumPost.fork();
        System.out.println("sumpost called");
        return sumPost.join() + sumPre.join();
    }
}
