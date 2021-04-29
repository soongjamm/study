package com.soongjamm.startboot.godOfJava.collections.practice;

import java.util.Hashtable;
import java.util.Random;
import java.util.Set;

public class RandomNumberStatistics {

    private final int DATA_BOUNDARY = 50;
    private Hashtable<Integer, Integer> hashTable = new Hashtable<>();

    public void printStatistics() {
        Set<Integer> keys = hashTable.keySet();
        for (Integer key : keys) {
            if (key % 10 - 1 == 0) {
                System.out.println();
            }
                System.out.print(String.format("%3d = %3d   | ", key, hashTable.get(key)));
        }

    }

    public void getRandomNumberStatistics() {
        Random random = new Random();
        for (int i = 0; i < 5000; i++) {
            putCurrentNumber(random.nextInt(DATA_BOUNDARY)+1);
        }
        printStatistics();
    }

    private void putCurrentNumber(int tempNumber) {
        hashTable.putIfAbsent(tempNumber, 0);
        hashTable.computeIfPresent(tempNumber, (key, value) -> value + 1);
    }

    public static void main(String[] args) {
        RandomNumberStatistics randomNumberStatistics = new RandomNumberStatistics();
        randomNumberStatistics.getRandomNumberStatistics();
    }
}
