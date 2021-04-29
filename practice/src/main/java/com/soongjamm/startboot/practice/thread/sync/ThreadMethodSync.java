package com.soongjamm.startboot.practice.thread.sync;

import lombok.SneakyThrows;

public class ThreadMethodSync extends Thread {

    Calculator calculator;

    public ThreadMethodSync(Calculator calculator) {
        this.calculator = calculator;
    }

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            calculator.add();
        }

        Thread.sleep(1000);
    }
}
