package com.soongjamm.startboot.objects_practice.chapter5.responsibility;

import java.time.Duration;

public class PercentDiscountMovie extends Movie {
    private double percent;

    public PercentDiscountMovie(String title, Duration duration, Money fee, double percent, DiscountCondition... discountConditions) {
        super(title, duration, fee, discountConditions);
        this.percent = percent;
    }

    @Override
    protected Money calculateDiscountAmount() {
        return getFee().times(percent);
    }
}
