package com.soongjamm.startboot.practice.thread.sync;

import lombok.SneakyThrows;

public class ThreadWithoutLock extends Thread {

    public static int sum = 0;

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            sum+=1;
        }
        System.out.println(sum);
    }
}
