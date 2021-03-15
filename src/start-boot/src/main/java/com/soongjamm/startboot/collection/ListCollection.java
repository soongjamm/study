package com.soongjamm.startboot.collection;

import java.util.ArrayList;
import java.util.LinkedList;


// https://okky.kr/article/567246
// LinkedList 의 중간삽입/삭제가 반드시 ArrayList 보다 빠르지 않다.
// LinkedList 의 삽입/삭제의 속도는 1이지만 해당 중간인덱스까지 찾아가는(순차탐색) 비용이 커서 인덱스값이 크면 ArrayList 보다 느리다.
public class ListCollection {
    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<>();
        LinkedList<String> l = new LinkedList<>();
        long start, end;

        // 초기 데이터 삽입
        for (int i = 0; i < 100000; i++) {
            a.add("");
        }
        for (int i = 0; i < 100000; i++) {
            l.add("");
        }

        // 1. ArrayList 중간에 삽입
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            a.add(0, "");
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);

        // 2. LinkedList 중간에 삽입
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            l.add(10000, "");
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}
