package com.soongjamm.startboot.godOfJava.string;

import java.util.Locale;

public class Problems {
    public static void main(String[] args) {
        Problems problems = new Problems();
        problems.printWorlds("this is soccer");
        problems.findString("The String class represents strings", "string");
        problems.findAnyCaseString("The String class represents strings", "string");
        problems.countChar("The String class represents strings", 's');
        problems.printContainWords("The String class represents strings", "ss");
    }

    public void printWorlds(String str) {
        for (String s : str.split(" ")) {
            System.out.println(s);
        }
    }

    public void findString(String str, String findStr) {
        System.out.println(str.indexOf(findStr));
    }

    public void findAnyCaseString(String str, String findStr) {
        System.out.println(str.toLowerCase(Locale.ROOT).indexOf(findStr));
    }

    public void countChar(String str, char c) {
        int count = 0 ;
        for (char ch : str.toCharArray()) {
            if (c == ch) {
                count++;
            }
        }
        System.out.println(count);
    }

    public void printContainWords(String str, String findStr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : str.split(" ")) {
            if (s.contains(findStr)) {
                stringBuilder.append(s + " ");
            }
        }
        System.out.println(stringBuilder.toString());
    }
}
