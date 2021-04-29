package com.soongjamm.startboot.designPattern.adapter.tires;

public class AwfulTire implements CarTire {
    @Override
    public void roll() {
        System.out.println("굉음을 내며 굴러간다.");
    }
}
