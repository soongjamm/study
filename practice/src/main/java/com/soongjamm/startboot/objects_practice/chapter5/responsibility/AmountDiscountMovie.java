package com.soongjamm.startboot.objects_practice.chapter5.responsibility;

import java.time.Duration;

public class AmountDiscountMovie extends Movie {
    private Money discountAmount;

    public AmountDiscountMovie(String title, Duration duration, Money fee, Money discountAmount, DiscountCondition... discountConditions) {
        super(title, duration, fee, discountConditions);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money calculateDiscountAmount() {
        return discountAmount;
    }
}
