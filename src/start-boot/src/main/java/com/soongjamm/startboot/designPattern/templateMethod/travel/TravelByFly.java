package com.soongjamm.startboot.designPattern.templateMethod.travel;

public class TravelByFly extends Travel {
    @Override
    protected void getOut() {
        System.out.println("비행기에서 내린다.");
    }

    @Override
    protected void getOn() {
        System.out.println("비행기에 탄다.");
    }

    @Override
    protected void buyTicket() {
        System.out.println("공항에서 티켓을 산다.");
    }
}
