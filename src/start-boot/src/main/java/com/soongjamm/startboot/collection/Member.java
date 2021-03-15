package com.soongjamm.startboot.collection;

import java.util.Objects;

public class Member implements Comparable {
    String name;
    int age;
    int favoriteNumber;

    public Member(String name, int age, int favoriteNumber) {
        this.name = name;
        this.age = age;
        this.favoriteNumber = favoriteNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return age == member.age && favoriteNumber == member.favoriteNumber && Objects.equals(name, member.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public int compareTo(Object o) {
        Member other = (Member) o;
        if (this.name.compareTo(other.name) == 0) {
            return this.age - other.age;
        }
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "name='" + name +
                ", age=" + age +
                ", favoriteNumber=" + favoriteNumber;
    }
}
