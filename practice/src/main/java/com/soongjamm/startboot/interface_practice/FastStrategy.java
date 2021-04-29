package com.soongjamm.startboot.interface_practice;

public class FastStrategy implements MovingStrategy {
    @Override
    public int move() {
        return Random.generate(5);
    }
}
