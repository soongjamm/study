package com.soongjamm.bank.shared;

public class Money {
    public static Money of(Long amount) {
        return new Money();
    }

    public static Money add(Money calculateBalance, Object negate) {
        return new Money();
    }

    public Object negate() {
        return null;
    }

    public boolean isPositive() {
        return false;
    }

    public Money getAmount() {
        return null;
    }

    public Object minus(Money transferredAmount) {
        return null;
    }

    public Object plus(Money transferredAmount) {
        return null;
    }
}
