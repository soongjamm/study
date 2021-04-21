package com.soongjamm.startboot.designPattern.decorator.simple;

public class AnotherService implements IService {
    @Override
    public String run() {
        System.out.println("Another Real Service");
        return "whooooooo!!!";
    }
}
