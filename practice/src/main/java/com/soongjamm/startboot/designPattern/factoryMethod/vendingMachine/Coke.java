package com.soongjamm.startboot.designPattern.factoryMethod.vendingMachine;

public class Coke implements Drinkable {
    @Override
    public void drink() {
        System.out.println("coke ~~ bulcokebulcoke");
    }
}
