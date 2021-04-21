package com.soongjamm.startboot.designPattern.decorator.simple;

public class Decorator implements IService {
    IService service;

    public Decorator(IService service) {
        this.service = service;
    }

    @Override
    public String run() {
        System.out.println("호출한 서비스의 리턴값을 포장해주는 데코레이터요");
        String run = service.run();
        System.out.println("run 끝~");
        return "MY - " + run;
    }
}
