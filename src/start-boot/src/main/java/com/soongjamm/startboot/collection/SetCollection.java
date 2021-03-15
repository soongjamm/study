package com.soongjamm.startboot.collection;

import java.util.*;

public class SetCollection {
    public static void main(String[] args) {
        Set<Member> set = new HashSet<>();
        set.add(new Member("soongjamm", 12, 1));
        set.add(new Member("soongjamm", 12, 1));
        set.add(new Member("soongjamm", 12, 2));
        set.add(new Member("soongjamm", 13, 1));
        System.out.println(set.size());

        set.remove(new Member("soongjamm", 12, 1));
        System.out.println(set.size());

        Iterator<Member> iterator = set.iterator();

        Set<Integer> integerSet = new HashSet<>();
        integerSet.addAll(Arrays.asList(1, 2, 3, 4));
        Set<Integer> another = new HashSet<>();
        another.add(5);

        integerSet.addAll(another);
        System.out.println(integerSet.size());

    }
}
