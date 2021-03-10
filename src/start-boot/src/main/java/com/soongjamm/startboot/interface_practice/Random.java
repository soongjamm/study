package com.soongjamm.startboot.interface_practice;

public class Random {
    public static int generate(int low) {
        int random = 0;
        while (random < low) {
            random = (int) (Math.random() * 10);
        }
        return random;
    }
}
