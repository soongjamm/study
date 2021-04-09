package com.soongjamm.startboot.godOfJava.lang_package;

public class NumberObjects {
    public static void main(String[] args) {
        NumberObjects numberObjects = new NumberObjects();
        long l = numberObjects.parseLong("5a");
        System.out.println(l);

        numberObjects.printOtherBase(10);
    }

    public long parseLong(String data) {
        long l = 0;
        try {
            l = Long.parseLong(data);
        } catch (NumberFormatException e) {
            System.out.println(data + " is not a number.");
        }
        return l;
    }

    public void printOtherBase(long value) {
        System.out.println("print other base");
        System.out.println(Long.toBinaryString(value));
        System.out.println(Long.toOctalString(value));
        System.out.println(Long.toHexString(value));
    }
}
