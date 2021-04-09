package com.soongjamm.startboot.godOfJava.collections;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetSample {
    public static void main(String[] args) {
        SetSample sample = new SetSample();
        String[] cars = new String[]{
                "Tico", "Sonata", "BMW", "Benz",
                "Lexus", "Mustang", "Grandeure",
                "The Beetle", "Mini Cooper", "i30",
                "BMW", "Lexus", "Carniabal", "SM5",
                "SM7", "SM3", "Tico"
        };
        System.out.println(sample.getCarKinds(cars));
    }

    public int getCarKinds(String[] cars) {
        HashSet<String> carSet = new HashSet<>(Arrays.asList(cars));
        printCarSet(carSet);
        printCarSetByIterator(carSet);
        return carSet.size();
    }

    public <T> void printCarSet(Set<T> cars) {
        for (T car : cars) {
            System.out.print(car + " ");
        }
        System.out.println();
    }

    public <T> void printCarSetByIterator(Set<T> cars) {
        Iterator<T> iterator = cars.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }
}
