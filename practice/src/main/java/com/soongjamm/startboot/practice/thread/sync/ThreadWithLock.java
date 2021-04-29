package com.soongjamm.startboot.practice.thread.sync;

import lombok.SneakyThrows;

public class ThreadWithLock extends Thread {

    private Object lock;

    public ThreadWithLock(Object lock) {
        // super("threadName");
        this.lock = lock;
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("before wait");
        synchronized (lock) {
            lock.wait();
        }
        System.out.println(this.getName() + " is notifiefd.");
        System.out.println(this.getName() + " is sleeping");
        Thread.sleep(1000);
        System.out.println(this.getName() + " woke up");
    }
}
