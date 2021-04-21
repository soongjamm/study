package com.soongjamm.startboot.designPattern.templateMethod.travel;

public class TravelByShip extends Travel {
    @Override
    protected void getOut() {
        System.out.println("배에서 내린다.");
    }

    @Override
    protected void getOn() {
        System.out.println("배에 탄다.");
    }

    @Override
    protected void buyTicket() {
        System.out.println("항구에서 티켓을 산다.");
    }
}
