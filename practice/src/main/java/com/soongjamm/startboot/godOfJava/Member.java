package com.soongjamm.startboot.godOfJava;

public class Member {
    int age;
    String name;

    public Member(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Member{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != getClass()) {
            return false;
        }
        if (o == this) {
            return true;
        }

        Member other = (Member) o;
        return this.name.equals(other.name) && this.age == other.age;

    }
}
