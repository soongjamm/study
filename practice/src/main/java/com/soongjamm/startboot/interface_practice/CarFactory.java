package com.soongjamm.startboot.interface_practice;

public class CarFactory {

    public static Car car() {
        return new Car(movingStrategy());
    }

    private static MovingStrategy movingStrategy() {
        return new FastStrategy(); // 자동차의 움직임을 바꾸고 싶다면 여기만 바꾸면 된다.
    }

}
