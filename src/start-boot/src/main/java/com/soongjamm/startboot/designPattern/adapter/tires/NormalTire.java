package com.soongjamm.startboot.designPattern.adapter.tires;

public class NormalTire implements BikeTire {
    @Override
    public void roll() {
        System.out.println("자전거 바퀴가  그냥 적당히 굴러간다.");
    }
}
