package com.soongjamm.startboot.basic;

public class NestedClass {

    String hello = "world";

    public static void main(String[] args) {
        NestedClass nestedClass = new NestedClass();
        nestedClass.run();

        NonStaticClass nonStaticClass = nestedClass.new NonStaticClass(); // 바깥 클래스가 있어야 중척 클래스 생성 가능
        StaticClass staticClass = new NestedClass.StaticClass(); // 바깥 클래스 없이 중첩 클래스 생성 가능

        class LocalClass {}
        LocalClass localClass = new LocalClass();
        System.out.println(localClass.getClass());
    }

    public void run() {
        NonStaticClass nonStaticClass = new NonStaticClass();
        System.out.println(this + " | " + nonStaticClass);
        nonStaticClass.print();
    }

    public String rootMethod() {
        return "rootMethod";
    }

    public class NonStaticClass {
        public void print() {
            System.out.println(this + " | " + rootMethod());
        }
    }

    public static class StaticClass {
        public void print() {
            // System.out.println(this + " | " + rootMethod()); // rootMethod() 불가능. static method 여야 호출가능,
        }
    }
}
