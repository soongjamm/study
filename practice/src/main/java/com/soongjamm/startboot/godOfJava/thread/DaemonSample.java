package com.soongjamm.startboot.godOfJava.thread;

import lombok.SneakyThrows;

public class DaemonSample extends Thread{
    @SneakyThrows
    @Override
    public void run() {
        System.out.println("daemon is running");
        sleep(Long.MAX_VALUE);
    }
}
