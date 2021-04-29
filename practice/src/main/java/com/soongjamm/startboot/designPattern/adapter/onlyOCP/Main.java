package com.soongjamm.startboot.designPattern.adapter.onlyOCP;

public class Main {
    public static void main(String[] args) {
        AClass a = new AClass();
        a.runA();

        BClass b = new BClass();
        b.runB();

        AClass ab = new AdapterA(b); // AClass 타입으로 B의 run을 수행함
        ab.runA();
    }
}
