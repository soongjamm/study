package com.soongjamm.startboot.godOfJava.nested_class;

public class NestedSample {
    public static void main(String[] args) {
        NestedSample sample = new NestedSample();
        sample.makeStaticNestedObject();
    }

    private void makeStaticNestedObject() {
        OuterOfStatic.staticNested staticNested = new OuterOfStatic.staticNested();
        staticNested.setValue(3);
        System.out.println(staticNested.getValue());
    }
}
