package com.soongjamm.startboot.collection.synchronizedCollection;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedList {
    /**
     * 한 쓰레드는 숫자를 탐색하고
     * 한 쓰레드는 동시에 그 숫자를 삭제하려 한다.
     * 만약 숫자 탐색을 성공하면 thread safe.
     */

    private static final int LOOP = 100000000;

    @SneakyThrows
    public static void main(String[] args) {
        ArrayList<Integer> notThreadSafe = new ArrayList<>();

        // thread unsafe
        run(notThreadSafe);

        // thread safe
        List<Integer> sync = Collections.synchronizedList(notThreadSafe);
        run(sync);
    }

    @SneakyThrows
    private static void run(List<Integer> list) {
        for (int i = 0; i < LOOP; i++) {
            list.add(i);
        }

        Thread add = new Thread(() -> {
            System.out.println("contains?  " + list.contains(LOOP-5));
        });

        Thread tryInterruption = new Thread(() -> {
            list.remove(LOOP-5);
        });

        add.start();
        tryInterruption.start();
    }
}
