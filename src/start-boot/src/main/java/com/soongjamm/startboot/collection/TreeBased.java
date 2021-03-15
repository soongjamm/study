package com.soongjamm.startboot.collection;

import java.util.ArrayList;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TreeBased {

    static final int THIRTY_MILLION = 30000000;

    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        long start, end;

        start = System.currentTimeMillis();
        for (int i = 0; i <= 100; i++) {
            treeSet.add(i);
        }
        end = System.currentTimeMillis();
        System.out.println(end - start); // 7903

        for (int i = 0; i <= 100; i++) {
            arrayList.add(i);
        }

        // TreeSet vs ArrayList 탐색 시간 비교
        System.out.println("------------");
        start = System.currentTimeMillis();
        System.out.println(treeSet.floor(100));
        end = System.currentTimeMillis();
        System.out.println(end - start); // 0 ~ 1

        start = System.currentTimeMillis();
        System.out.println(arrayList.contains(100));
        end = System.currentTimeMillis();
        System.out.println(end - start); // 70 ~ 74
        System.out.println("------------");

        NavigableSet<Integer> under = treeSet.headSet(50, true).descendingSet();
        System.out.println(under.pollFirst()); // 50까지 중 마지막 숫자

        NavigableSet<Integer> over = treeSet.tailSet(50, true).descendingSet();
        System.out.println(over.pollFirst()); // 50이상 중 마지막 숫자

        TreeSet<Member> members = new TreeSet<>();
        members.add(new Member("soongjamm", 28, 3));
        members.add(new Member("soongjamm", 22, 1));
        members.add(new Member("bobby", 29, 4));
        members.add(new Member("byrd", 15, 7));
        members.add(new Member("ronaldo", 50, 99));

        for (Member member : members) {
            System.out.println(member.toString());
        }

    }
}
