package com.soongjamm.startboot.designPattern.adapter.powerSocket;

public class AdapterFor110V implements For220V {
    For110V item;

    public AdapterFor110V(For110V item) {
        this.item = item;
    }

    @Override
    public void plug() {
        System.out.println("\n이것들은 좀 느리게 충전됌..");
        item.plug();
    }
}
