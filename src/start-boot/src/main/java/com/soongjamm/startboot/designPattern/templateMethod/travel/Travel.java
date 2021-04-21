package com.soongjamm.startboot.designPattern.templateMethod.travel;

public abstract class Travel {

    // template method
    public final void depart() {
        decideDestination();
        buyTicket();
        getOn();
        getOut();
    }

    protected abstract void getOut();

    protected abstract void getOn();

    protected abstract void buyTicket();

    protected void decideDestination() { // hooking
        System.out.println("목적지를 정한다 : 미국");
    }
}
