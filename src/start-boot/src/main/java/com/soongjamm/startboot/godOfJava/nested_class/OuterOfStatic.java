package com.soongjamm.startboot.godOfJava.nested_class;

public class OuterOfStatic {
    static class staticNested {
        private int value = 0;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
