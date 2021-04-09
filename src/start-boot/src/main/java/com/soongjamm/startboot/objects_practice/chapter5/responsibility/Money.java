package com.soongjamm.startboot.objects_practice.chapter5.responsibility;

public class Money {
    public static final Money ZERO = Money.wons(0);

    private static Money wons(int i) {
        return Money.ZERO;
    }

    public Money times(double discountPercent) {
        return Money.ZERO;
    }

    public Money minus(Money calculateDiscountAmount) {
        return Money.ZERO;
    }
}
