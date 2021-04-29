package com.soongjamm.startboot.godOfJava;

public class PassByValue {
    public static void passByValue(int a, String b) {
        a = 100;
        b = "bye";
        System.out.println(a + b);
    }

    public static void passByValue(Member member) {
        member.age = 5;
        member.name = "bob";
        System.out.println(member.toString());
    }

    public static void main(String[] args) {
        int a = 0;
        String b = "hi";
        System.out.println(a + b);
        passByValue(a, b);
        System.out.println(a + b);

        Member alice = new Member(100, "alice");
        System.out.println(alice.toString());
        passByValue(alice);
        System.out.println(alice.toString());
    }
}
