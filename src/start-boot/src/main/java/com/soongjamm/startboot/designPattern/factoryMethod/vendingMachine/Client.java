package com.soongjamm.startboot.designPattern.factoryMethod.vendingMachine;

public class Client {
    public static void main(String[] args) {
        BaseDrinkFactory factory = new DrinkFactory();
        Drinkable drinkable1 = factory.takeBeverage(1);
        Drinkable drinkable2 = factory.takeBeverage(2);

        drinkable1.drink();
        drinkable2.drink();
    }
}
