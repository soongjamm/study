package com.soongjamm.startboot.godOfJava.string;

public class StringMethods {
    public static void main(String[] args) {
        System.out.println("한글3456쀏뿗9".length()); // 9

        System.out.println("IS EMPTY".isEmpty()); // false

        System.out.println(" ".isEmpty()); // false 공백이라도 있으므로
        System.out.println(" ".isBlank()); // true 공백은 blank

        System.out.println("".isEmpty()); // true
        System.out.println("".isBlank()); // true

        checkEquality();
        checkCompare();

    }

    private static void checkCompare() {
        String target = "a";

        String d = "d";
        String D = "D";
        String 가 = "가";

        System.out.println("\n--checkCompare()--");
        System.out.println(target.compareTo(d));
        System.out.println(d.compareTo(target));
        System.out.println("a - D : " + target.compareTo(D));
        System.out.println(target.compareToIgnoreCase(D));
        System.out.println(target.compareTo(가));
    }

    private static void checkEquality() {
        String target = "check";

        String s2 = "check";
        String s3 = new String("check");
        String s4 = "Check";

        System.out.println("\n--checkEquality())--");
        System.out.println(target == s2); // true
        System.out.println(target == s3); // false
        System.out.println(target.equalsIgnoreCase(s4)); // true
    }
}
