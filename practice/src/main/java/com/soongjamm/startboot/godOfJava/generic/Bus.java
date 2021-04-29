package com.soongjamm.startboot.godOfJava.generic;

public class Bus extends Car{
    String name;

    public Bus(String name) {
        super(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
