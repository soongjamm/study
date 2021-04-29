package com.soongjamm.startboot.objects_practice.chapter2_completedVersion;

public class NoneDiscountPolicy extends DiscountPolicy {

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
