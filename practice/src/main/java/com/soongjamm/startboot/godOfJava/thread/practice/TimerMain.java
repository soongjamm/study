package com.soongjamm.startboot.godOfJava.thread.practice;

public class TimerMain {
    public static void main(String[] args) {
        TimerMain timer = new TimerMain();
        timer.timerOn();
    }

    public void timerOn(){
        Thread thread = new TimeThread();
        thread.start();
    }
}
