package com.soongjamm.startboot.practice.thread.volatileExample;

import lombok.SneakyThrows;

public class Volatile {
    @SneakyThrows
    public static void main(String[] args) {
        VolatileThread volatileThread = new VolatileThread();
        volatileThread.start();
        Thread.sleep(100);
        volatileThread.setNum(1);
        System.out.println("set num ok");
    }
}
