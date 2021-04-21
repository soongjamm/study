package com.soongjamm.startboot.designPattern.adapter.powerSocket;

public class KoreaIPhoneCharger implements For220V {

    @Override
    public void plug() {
        System.out.println("korea iphone charging");
    }
}
