package com.soongjamm.startboot.practice.thread.threadLocal;

import java.util.Random;

public class ThreadLocalSample {
    private final static ThreadLocal<Integer> local = new ThreadLocal<>();
    private static Random random;
    static {
         random = new Random();
    }

    public static Integer generateNumber() {
        int value = random.nextInt(45);
        local.set(value);
        return value;
    }

    public static Integer get(){
        return local.get();
    }

    public static void remove() {
        local.remove();
    }

    public static void main(String[] args) {
        LocalUserThread thread = new LocalUserThread();
        thread.start();
    }
}
