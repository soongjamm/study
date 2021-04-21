package com.soongjamm.startboot.designPattern.templateMethod.travel;

public class Main {
    public static void main(String[] args) {
        Travel ship = new TravelByShip();
        ship.depart();

        System.out.println();

        Travel fly = new TravelByFly();
        fly.depart();


    }
}
