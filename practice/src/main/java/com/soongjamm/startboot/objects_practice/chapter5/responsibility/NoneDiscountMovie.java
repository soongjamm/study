package com.soongjamm.startboot.objects_practice.chapter5.responsibility;

import java.time.Duration;

public class NoneDiscountMovie extends Movie {
    public NoneDiscountMovie(String title, Duration duration, Money fee, DiscountCondition... discountConditions) {
        super(title, duration, fee, discountConditions);
    }

    @Override
    protected Money calculateDiscountAmount() {
        return Money.ZERO;
    }
}
