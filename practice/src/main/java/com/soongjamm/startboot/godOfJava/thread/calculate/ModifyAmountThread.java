package com.soongjamm.startboot.godOfJava.thread.calculate;


public class ModifyAmountThread extends Thread {

    private Calculator calculator;
    private boolean addFlag;

    public ModifyAmountThread(Calculator calculator, boolean addFlag) {
        this.calculator = calculator;
        this.addFlag = addFlag;
    }

    @Override
    public void run() {
        Thread.currentThread().interrupt();
        for (int i = 0; i < 10000; i++) {
            calculator.plus(1); // 왜 이게 더 빠르지?

//            calculator.plus2(1);
//            calculator.minus(1);
        }
        System.out.println(Thread.currentThread().getState());
    }
}
