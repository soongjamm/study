package com.soongjamm.startboot.godOfJava.string;

public class Contains {
    public static void main(String[] args) {
        String text = "coffee java wawa";
        String compare1 = "java";

        System.out.println(text.regionMatches(7, compare1, 0, compare1.length())); // true 정확히 일치해야 함
        System.out.println(text.regionMatches(6, compare1, 0, compare1.length()+1)); // false

    }
}
