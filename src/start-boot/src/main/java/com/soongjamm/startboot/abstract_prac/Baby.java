package com.soongjamm.startboot.abstract_prac;

public class Baby extends Person {
    @Override
    public void sleep() {
        System.out.println("엄마 품에서 잔다.");
    }

    @Override
    public void sleep(String msg) {
        System.out.println(msg);
    }
}
