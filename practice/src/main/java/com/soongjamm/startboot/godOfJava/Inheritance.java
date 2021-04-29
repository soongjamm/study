package com.soongjamm.startboot.godOfJava;

import java.math.BigDecimal;

class Parent {
    public Parent(String name) {
        System.out.println("parent call");
    }
}

class Children extends Parent {
    public Children() {
        super("bob"); // 부모의 기본생성자를 호출하거나 super()로 생성자를 명시해서 부모생성자를 호출해야한다.
        System.out.println("children call");
    }
}

public class Inheritance {
    public static void main(String[] args) {
        Children children = new Children(); // 부모 생성자 - 자식 생성자 순 호출 됌

        BigDecimal bigDecimal = new BigDecimal(9.99);
        System.out.println(bigDecimal.scale());
    }
}
