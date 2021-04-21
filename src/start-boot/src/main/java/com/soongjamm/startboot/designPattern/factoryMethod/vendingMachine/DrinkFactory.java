package com.soongjamm.startboot.designPattern.factoryMethod.vendingMachine;

public class DrinkFactory extends BaseDrinkFactory {

    @Override
    public Drinkable takeBeverage(int number) {
        Drinkable drink;
        if (number == 1) {
            drink = new Coke();
        } else {
            drink = new Sprite();
        }
        System.out.println("Wrapping ..");
        return drink;
    }
}
