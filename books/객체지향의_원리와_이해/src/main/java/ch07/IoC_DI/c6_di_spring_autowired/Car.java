package ch07.IoC_DI.c6_di_spring_autowired;

import org.springframework.beans.factory.annotation.Autowired;

public class Car {
    @Autowired
    Tire tire;

    public String getTireBrand() {
        return "장착된 타이어: " + tire.getBrand();
    }
}
