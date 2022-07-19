package com.soongjamm.startboot.specification_pattern.eternity.domain;



public class Atmosphere {
    private Money price;
    private Element element1;
    private Element element2;

    public Atmosphere(Money wons, Object n, Object o) {

    }

    public static Element element(String name, Ratio ratio) {
        return new Element(name, ratio);
    }

    public static class Element {
        private String name;
        private Ratio ratio;

        public Element(String name, Ratio ratio) {
            this.name = name;
            this.ratio = ratio;
        }
    }
}
