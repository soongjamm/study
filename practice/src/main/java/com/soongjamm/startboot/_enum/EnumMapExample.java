package com.soongjamm.startboot._enum;

import com.soongjamm.startboot._enum.Phase.Transition;

public class EnumMapExample {
    public static void main(String[] args) {
        Transition from = Transition.from(Phase.GAS, Phase.LIQUID);
        System.out.println(from);
    }
}
