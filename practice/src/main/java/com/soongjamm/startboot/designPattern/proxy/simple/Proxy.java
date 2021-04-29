package com.soongjamm.startboot.designPattern.proxy.simple;

public class Proxy implements IService {
    IService service;

    public Proxy(IService service) {
        this.service = service;
    }

    @Override
    public String run() {
        System.out.println("호출에 대한 흐름제어가 주 목적, 반환 결과를 그대로 전달. (ex. 여기서 어떤 작업을 처리 후 service를 실행하도록.)");
        String run = service.run();
        System.out.println("run 끝.");
        return run;
    }
}
