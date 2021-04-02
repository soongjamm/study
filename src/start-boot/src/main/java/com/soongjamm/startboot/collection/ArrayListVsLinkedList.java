package com.soongjamm.startboot.collection;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * https://okky.kr/article/567246
 * LinkedList 의 중간삽입/삭제가 반드시 ArrayList 보다 빠르지 않다.
 * LinkedList 의 삽입/삭제의 속도는 1이지만 해당 중간인덱스까지 찾아가는(순차탐색) 비용이 커서 인덱스값이 크면 ArrayList 보다 느리다.
 */
public class ArrayListVsLinkedList {

    private static final int LOOP = 200000;

    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<>();
        LinkedList<String> l = new LinkedList<>();
        long start, end;

        /**
         * 삽입
         * ArrayList는 인덱스를 가지고 마지막에 추가만 하므로 빠름. (그냥 추가는 배열을 밀지 않아도 됨)
         * LinkedList는 마지막 노드를 탐색해서 추가해줘야 하기 때문에 좀 느림.
         */
        start = System.currentTimeMillis();
        for (int i = 0; i < LOOP; i++) {
            a.add("");
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList 값 설정 " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < LOOP; i++) {
            l.add("");
        }
        end = System.currentTimeMillis();
        System.out.println("LinkedList 값 설정 " + (end - start));

        /**
         *  (앞쪽 인덱스에) 값을 추가하는 경우 LinkedList는 추가만 하는 O(1)이라 엄청빠르지만
         *  ArrayList는 뒤에 값을 다 밀어줘야해서 오래걸린다. (거의 200배까지도 차이남)
         */
        // 앞쪽 인덱스
        start = System.currentTimeMillis();
        for (int i = 0; i < LOOP; i++) {
            a.add(0, "");
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList 앞쪽 인덱스에 대량으로 추가 " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < LOOP; i++) {
            l.add(0, "");
        }
        end = System.currentTimeMillis();
        System.out.println("LinkedList 앞쪽 인덱스에 대량으로 추가 " + (end - start));

        /**
         *  추가하려는 인덱스가 뒤에있는 경우,
         *  LinkedList는 순차적으로 그 인덱스까지 탐색을 해야해서 오히려 오래걸리게 된다.
         *  ArrayList는 앞에 넣나 뒤에 넣나 차이가 없다.
         *
         *  (LinkedList는 앞에 넣을때보다 1500배 이상 느려지고, ArrayList의 거의 10배 느림)
         */
        // 뒷쪽 인덱스
        start = System.currentTimeMillis();
        for (int i = 0; i < LOOP; i++) {
            a.add(LOOP - 10, "");
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList 뒷쪽 인덱스에 대량으로 추가 " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < LOOP; i++) {
            l.add(LOOP - 10, "");
        }
        end = System.currentTimeMillis();
        System.out.println("LinkedList 뒷쪽 인덱스에 대량으로 추가 " + (end - start));


    }
}
