package com.soongjamm.startboot.interface_practice;

public class SlowStrategy implements MovingStrategy{
    @Override
    public int move() {
        return Random.generate(0);
    }
}
