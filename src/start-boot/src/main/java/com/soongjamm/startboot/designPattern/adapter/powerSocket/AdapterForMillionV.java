package com.soongjamm.startboot.designPattern.adapter.powerSocket;

public class AdapterForMillionV implements For220V {

    ForMillionV item;

    public AdapterForMillionV(ForMillionV item) {
        this.item = item;
    }

    @Override
    public void plug() {
        System.out.println("\n이것들은 특별관리가 필요함. 전기충전 2배속!!!");
        item.superPowerPlug();
        item.superPowerPlug();
    }
}
