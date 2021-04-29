package com.soongjamm.startboot.designPattern.adapter.powerSocket;

public class PowerSocket220V {

    static For220V[] power220 = new For220V[3]; // 2구짜리 콘센트

    public static void main(String[] args) {
        power220[0] = new KoreaIPhoneCharger();
        power220[1] = new AdapterFor110V(new AmericaIphoneCharger());
        power220[2] = new AdapterForMillionV(new Pikachu());

        for (int i = 0; i < power220.length; i++) {
            power220[i].plug();
        }
    }
}
