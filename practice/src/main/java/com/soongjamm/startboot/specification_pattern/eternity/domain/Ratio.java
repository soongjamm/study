package com.soongjamm.startboot.specification_pattern.eternity.domain;

public class Ratio {
    private double value;

    public Ratio(double value) {
        this.value = value;
    }

    public static Ratio of(double value) {
        return new Ratio(value);
    }
}
