package com.soongjamm.startboot.godOfJava.exception;

public class Calculator {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        try {
            calc.printDivide(1,2);
            calc.printDivide(1,0);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void printDivide(double d1, double d2) throws ArrayIndexOutOfBoundsException{
        if (d2 == 0) {
            throw new IllegalArgumentException("Second value can't be Zero.");
        }
        double result = d1/d2;
        System.out.println(result);
//        int[] arr = new int[0];
//        arr[0]=1;
    }
}
