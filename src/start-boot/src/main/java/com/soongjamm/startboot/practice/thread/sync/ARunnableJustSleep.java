package com.soongjamm.startboot.practice.thread.sync;

import lombok.SneakyThrows;

public class ARunnableJustSleep implements Runnable {
    public static int sum = 0;

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("before sleep");
        Thread.sleep(2000); // daemon 쓰레드 2초뒤 종료됌
        System.out.println("interrupted() in Thread " + Thread.interrupted());
        System.out.println("sleep thread stopped");
    }
}
