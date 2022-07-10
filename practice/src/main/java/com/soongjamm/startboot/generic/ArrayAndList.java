package com.soongjamm.startboot.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ArrayAndList {

    public static void main(String[] args) {
        Chooser<String> chooser = new Chooser<>();
        chooser.setListChooser(new ArrayList<>(List.of("Hello")));
        chooser.setArrayChooser(new ArrayList<>(List.of("Hello")));
        chooser.choose();
    }

    public static class Chooser<T> {
        private List<T> choiceList;
        private T[] choiceArray;

        public void setListChooser(Collection<T> collection) {
            this.choiceList = new ArrayList<>(collection);
        }

        public void setArrayChooser(Collection<T> collection ) {
            // this.choiceArray = collection.toArray(new T[0]); // runtime 에 T 가 존재해야 new T[0] 를 하는데, 소거되버림
            @SuppressWarnings("unchecked") T[] choiceArray = (T[]) collection.toArray();
            this.choiceArray = choiceArray;
        }

        public T choose() {
            Random rnd = ThreadLocalRandom.current();
            return choiceList.get((rnd.nextInt(choiceList.size())));
        }
    }

    @SafeVarargs // Not actually safe!
    static void m(List<String>... stringLists) {
        Object[] array = stringLists;
        List<Integer> tmpList = Arrays.asList(42);
        array[0] = tmpList; // Semantically invalid, but compiles without warnings
        String s = stringLists[0].get(0); // Oh no, ClassCastException at runtime!
    }
}
