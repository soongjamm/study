package com.soongjamm.startboot.collection;

import java.util.HashSet;

public class HashSetLoadFactor {

    private static final int LOOP = 50000000;

    /**
     * HashSet의 Load Factor에 따른 성능차이를 확인해본다.
     * 0.75f 가 가장 이상적인 값이고 건들지 않는게 좋다고 한다. (디폴트)
     */

    public static void main(String[] args) {
        HashSet<Integer> hashSet = new HashSet<>(10000000, 2f);
        long start, end;

        start = System.currentTimeMillis();
        for (int i = 0; i < LOOP; i++) {
            hashSet.add(i);
        }
        end = System.currentTimeMillis();
        System.out.println("(end-start) = " + (end-start));
        // (10,1) 4823
        // (10,10) 28476
        // (1000, 10) 27397
        // (10000, 10) 26471
        // (10000, 1) 4942
        // (10000000, 10) 4597
        // (10000000, 1) 4378
        // (10000000) 4221
        // (10000000, 0.5f) 5128
        // (10000000, 1f) 4489
        // (10000000, 2f) 5381
    }
}
