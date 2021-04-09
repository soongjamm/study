package com.soongjamm.startboot.practice.thread.volatileExample;

public class VolatileThread extends Thread {
    volatile private int num=0;

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        while(num==0);
        System.out.println("setNum is applied "+num);
    }
}
