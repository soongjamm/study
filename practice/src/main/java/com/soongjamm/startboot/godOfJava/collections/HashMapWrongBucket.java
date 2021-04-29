package com.soongjamm.startboot.godOfJava.collections;

import java.util.HashMap;

public class HashMapWrongBucket {
    public static void main(String[] args) {
        MutableKey key = new MutableKey("original");
        HashMap<MutableKey, String> hashSet = new HashMap<>();
        hashSet.put(key, "success");
        key.setName("name changed");
        String s = hashSet.get(key);
        System.out.println(s);
    }
}
