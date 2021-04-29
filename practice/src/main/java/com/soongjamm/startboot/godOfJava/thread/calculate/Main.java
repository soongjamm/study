package com.soongjamm.startboot.godOfJava.thread.calculate;

import lombok.SneakyThrows;

public class Main {
    public static void main(String[] args) {
        Main m = new Main();
        m.calculate();
    }

    @SneakyThrows
    public void calculate() {
        long start = System.currentTimeMillis();

        Calculator calculator = new Calculator();
        Thread one = new ModifyAmountThread(calculator, true);
        Thread two = new ModifyAmountThread(calculator, true);
        one.start();
        two.start();
        //        try {
//            one.join();
//            two.join();
//            System.out.println("Final value is " + calculator.getAmount());
//            System.out.println(Thread.currentThread().getState());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
