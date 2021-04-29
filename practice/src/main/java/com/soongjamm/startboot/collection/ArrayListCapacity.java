package com.soongjamm.startboot.collection;

import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ArrayListCapacity {

    private static final int LOOP = 100000000;

    @SneakyThrows
    public static void main(String[] args) {
        ArrayList<Integer> al1 = new ArrayList<>(10);
        ArrayList<Integer> al2 = new ArrayList<>(LOOP);

        capacitySpeed(al1, al2);
        showTrimToSize(al2);
    }

    private static void capacitySpeed(ArrayList<Integer> al1, ArrayList<Integer> al2) {
        long start;
        long end;
        /**
         * 원래 추가하는 속도가 빠른 ArrayList 지만
         * 애초에 크기를 크게 잡아준 리스트가 (1억회기준) 5배정도 빨랐다.
         */
        start = System.currentTimeMillis();
        for (int i = 0; i < LOOP; i++) {
            al1.add(i);
        }
        end = System.currentTimeMillis();
        System.out.println("초기 크기 10인 AL = " + (end-start));


        start = System.currentTimeMillis();
        for (int i = 0; i < LOOP; i++) {
            al2.add(i);
        }
        end = System.currentTimeMillis();
        System.out.println("초기 크기 대용량 AL = " + (end-start));
    }

    // 결과가 다르길 기대했는데... 같다.
    private static void showTrimToSize(ArrayList<Integer> al2) throws IOException {
        FileOutputStream fos = new FileOutputStream("/Users/soongjamm/test.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(al2);

        al2.trimToSize();
        fos = new FileOutputStream("/Users/soongjamm/test2.txt");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(al2);
        oos.close();

        long size1 = Files.size(Path.of("/Users/soongjamm/test.txt"));
        long size2 = Files.size(Path.of("/Users/soongjamm/test2.txt"));
        System.out.println(size1);
        System.out.println(size2);
    }
}
