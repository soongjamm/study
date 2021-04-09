package com.soongjamm.startboot.practice.thread.sync;

public class Calculator {
    private int value = 0;

    @Override
    public String toString() {
        return "Calculator{" +
                "value=" + value +
                '}';
    }

    public void add() {
        synchronized (this) {
            value++;
        }
    }
}
