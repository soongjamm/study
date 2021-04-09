package com.soongjamm.startboot.godOfJava.thread;

import lombok.SneakyThrows;

public class SleepThread extends Thread {
    long sleepTime;

    public SleepThread(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    @SneakyThrows
    public void run() {
//        System.out.println("am i interrupted? - " + interrupted());
            System.out.println("Sleeping " + getName());
            Thread.sleep(sleepTime);
            System.out.println("Stopping " + getName());
    }
}
