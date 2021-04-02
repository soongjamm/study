package com.soongjamm.startboot.collection;

import java.util.ArrayList;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TreeSetVsArrayList {

    static final int THIRTY_MILLION = 30000000;

    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        long start, end;

        /**
         * 삽입
         * treeset은 추가할 때 마다 데이터를 밀고 이동시켜야해서 오래걸림
         * arraylist는 순차적으로 추가하므로 비교적 빠름
         */
        // treeset에 삽입
        start = System.currentTimeMillis();
        for (int i = 0; i <= THIRTY_MILLION; i++) {
            treeSet.add(i);
        }
        end = System.currentTimeMillis();
        System.out.println("treeset 삽입 : " + (end - start));


        // arraylist에 삽입
        start = System.currentTimeMillis();
        for (int i = 0; i <= THIRTY_MILLION; i++) {
            arrayList.add(i);
        }
        end = System.currentTimeMillis();
        System.out.println("arraylist 삽입 " + (end - start));


        // TreeSet vs ArrayList 탐색 시간 비교.
        System.out.println("------------");
        start = System.currentTimeMillis();
        treeSet.contains(THIRTY_MILLION);
        end = System.currentTimeMillis();
        System.out.println("treeset : " + (end - start));

        start = System.currentTimeMillis();
        arrayList.contains(THIRTY_MILLION);
        end = System.currentTimeMillis();
        System.out.println("arraylist " + (end - start));
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
