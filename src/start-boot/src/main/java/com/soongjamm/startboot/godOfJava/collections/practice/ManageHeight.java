package com.soongjamm.startboot.godOfJava.collections.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ManageHeight {

    private ArrayList<ArrayList<Integer>> gradeHeights;

    public void setGradeHeights() {
        gradeHeights = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            gradeHeights.add(new ArrayList<>());
        }
        gradeHeights.get(0).addAll(Arrays.asList(new Integer[]{170, 180, 173, 175, 177}));
        gradeHeights.get(1).addAll(Arrays.asList(new Integer[]{160, 165, 167, 186}));
        gradeHeights.get(2).addAll(Arrays.asList(new Integer[]{158, 177, 187, 176}));
        gradeHeights.get(3).addAll(Arrays.asList(new Integer[]{173, 182, 181}));
        gradeHeights.get(4).addAll(Arrays.asList(new Integer[]{170, 180, 165, 177, 172}));
    }

    public void printHeight(int classNo) {
        ArrayList<Integer> students = gradeHeights.get(classNo - 1);
        System.out.println("Class No.:" + classNo);
        for (Integer height : students) {
            System.out.println(height);
        }
    }

    public void printAverage(int classNo) {
        double sum = 0;
        ArrayList<Integer> students = gradeHeights.get(classNo);
        for (Integer height : students) {
            sum += height;
        }
        System.out.println("Class No.:" + classNo);
        System.out.println(sum/students.size());
    }


    public static void main(String[] args) {
        ManageHeight manageHeight = new ManageHeight();
        manageHeight.setGradeHeights();

        for (int i = 1; i <= 5; i++) {
            manageHeight.printHeight(i);
        }

        while(manageHeight.gradeHeights.size() > 0) {
            manageHeight.printAverage(0);
            manageHeight.gradeHeights.remove(0);
        }

    }

}
