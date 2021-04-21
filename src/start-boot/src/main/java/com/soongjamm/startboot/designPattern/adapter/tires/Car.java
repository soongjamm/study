package com.soongjamm.startboot.designPattern.adapter.tires;

import java.util.ArrayList;

public class Car {
    public static void main(String[] args) {
        ArrayList<CarTire> tires = new ArrayList<>();
        tires.add(new SuperTire());
        tires.add(new SuperTire());
        tires.add(new AwfulTire());
        tires.add(new BikeTireAdapter(new NormalTire()));

        tires.forEach((x) -> x.roll());

    }
}
