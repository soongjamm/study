package com.soongjamm.startboot.designPattern.adapter.tires;

public class BikeTireAdapter implements CarTire {

    BikeTire bikeTire;

    public BikeTireAdapter(BikeTire bikeTire) {
        this.bikeTire = bikeTire;
    }

    @Override
    public void roll() {
        bikeTire.roll();
    }
}
