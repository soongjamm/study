package com.soongjamm.startboot.godOfJava.java7;

import java.util.Arrays;
import java.util.StringJoiner;

public class StringJoinerSample {
    public static void main(String[] args) {

        StringJoiner joiner = new StringJoiner(",","(",")");
        String[] arr = {"안녕","하","십니","까요"};
        for (String s : arr) {
            joiner.add(s);
        }
        System.out.println(joiner.toString());
    }
}
