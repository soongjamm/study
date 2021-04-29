package com.soongjamm.startboot.godOfJava.thread.calculate;

public class Calculator {
    private int amount;

    public Calculator() {
        this.amount = 0;
    }

    public synchronized void plus(int value) {
        amount += value;
    }

    public void plus2(int value) {
        synchronized (this) {
            amount += value;
        }
    }

    public synchronized void minus(int value) {
        amount -= value;
    }

    public int getAmount() {
        return amount;
    }
}
