package com.soongjamm.startboot.godOfJava.thread;

public class ThreadSample extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("extends thread");
    }
}
