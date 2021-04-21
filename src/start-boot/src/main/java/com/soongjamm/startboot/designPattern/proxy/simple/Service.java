package com.soongjamm.startboot.designPattern.proxy.simple;

public class Service implements IService {
    @Override
    public String run() {
        System.out.println("real service is running");
        return "something";
    }
}
