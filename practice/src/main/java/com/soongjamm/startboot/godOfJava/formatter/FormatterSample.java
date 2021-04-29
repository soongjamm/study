package com.soongjamm.startboot.godOfJava.formatter;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class FormatterSample {
    public static void main(String[] args) {
        FormatterSample formatter = new FormatterSample();
//        formatter.numberFormat();
        formatter.decimalFormat();
    }

    private void decimalFormat() {
        String pattern="0,0000.00";

        double number= 123.456;
        printDecimalFormat(pattern, number);
        number= 1;
        printDecimalFormat(pattern, number);
        number= 123.4567890;
        printDecimalFormat(pattern, number);
        number= 123456789.456;
        printDecimalFormat(pattern, number);

        pattern = "##,000.####;(###.###)";
        number= 12.456;
        printDecimalFormat(pattern, number);
        number= 121212123.456;
        printDecimalFormat(pattern, number);
        number= -12.456;
        printDecimalFormat(pattern, number);


    }

    private void printDecimalFormat(String pattern, double number) {
        DecimalFormat format = new DecimalFormat(pattern);
        String result = format.format(number);
        System.out.println("pattern: " + pattern + " numbr: " + number + " result: " + result);
    }

    public void numberFormat() {
        double number = 1234567890.54321;
        NumberFormat num = NumberFormat.getInstance();
        num.setMinimumFractionDigits(6);
        NumberFormat cur = NumberFormat.getCurrencyInstance();
        NumberFormat integer = NumberFormat.getIntegerInstance();
        NumberFormat percent = NumberFormat.getPercentInstance();
        String s1 = num.format(number);
        String s2 = cur.format(number);
        String s3 = integer.format(number);
        String s4 = percent.format(0.97);
        System.out.println("numberFormat : " +  s1);
        System.out.println("currency : " +  s2);
        System.out.println("int format : " +  s3);
        System.out.println("percent format : " +  s4);
    }
}
