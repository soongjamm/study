package com.soongjamm.startboot.godOfJava.nested_class;

public class InnerSample {
    public static void main(String[] args) {
        InnerSample sample = new InnerSample();
        sample.makeInnerObject();
    }

    public void makeInnerObject() {
        // outer 클래스를 먼저 만들어야 그 내부에 있는 inner 클래스 객체를 생성할 수 있다.
        OuterOfInner outer = new OuterOfInner();
        OuterOfInner.Inner inner = outer.new Inner();
        inner.setValue(3);
        System.out.println(inner.getValue());
    }
}
