package com.soongjamm.startboot.designPattern.factoryMethod.vendingMachine;

public class Sprite implements Drinkable{
    @Override
    public void drink() {
        System.out.println("sprite bulkuk");
    }
}
