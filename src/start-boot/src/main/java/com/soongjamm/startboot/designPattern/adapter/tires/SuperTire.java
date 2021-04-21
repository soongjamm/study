package com.soongjamm.startboot.designPattern.adapter.tires;

public class SuperTire implements CarTire {
    @Override
    public void roll() {
        System.out.println("부드럽게 굴러간다.");
    }
}
