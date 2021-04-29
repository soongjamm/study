package com.soongjamm.startboot.godOfJava.collections;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapSample {
    public static void main(String[] args) {
        TreeMapSample treeMapSample = new TreeMapSample();
        treeMapSample.checkTreeMap();
    }

    public void checkTreeMap() {
        // 키가 숫자-대문자-소문자-한글 순으로 정렬된다.
        TreeMap<String, String> map = new TreeMap<>();
        map.put("A", "a");
        map.put("가", "e");
        map.put("1", "f");
        map.put("a", "g");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey() + " : " + entry.getValue() );
        }

    }
}
