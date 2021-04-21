package com.soongjamm.startboot.designPattern.adapter.powerSocket;

public class AmericaIphoneCharger implements For110V {
    @Override
    public void plug() {
        System.out.println("america iphone 110v charging");
    }
}
