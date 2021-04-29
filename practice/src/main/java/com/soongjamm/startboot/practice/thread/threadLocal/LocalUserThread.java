package com.soongjamm.startboot.practice.thread.threadLocal;

public class LocalUserThread extends Thread {
    @Override
    public void run() {
        int value = ThreadLocalSample.generateNumber();
        System.out.println(this.getName() + " LocalUserThread value = " + value);

        OtherLogic otherLogic = new OtherLogic();
        // ThreadLocal을 안쓰면 값을 파라미터로 줘야한다.
        // 만약 그 안에서 또 다른 메소드가 동일한 값을 쓰려면 또 넘겨줘야 한다. 구현이 복잡해진다.
        otherLogic.printMyNumber();
        ThreadLocalSample.remove();
    }

}
