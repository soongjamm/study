package com.soongjamm.startboot.interface_practice;


public class Car {

    private MovingStrategy movingStrategy;
    private int position;

    public Car(MovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
        this.position = 0;
    }

    public void move() {
        position += movingStrategy.move();
    }

    public int getPosition() {
        return position;
    }
}
