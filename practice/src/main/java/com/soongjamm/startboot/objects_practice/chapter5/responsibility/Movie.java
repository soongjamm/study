package com.soongjamm.startboot.objects_practice.chapter5.responsibility;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public abstract class Movie {
    private String title;
    private Duration duration;
    private Money fee;
    private List<DiscountCondition> discountConditions;

    public Movie(String title, Duration duration, Money fee, DiscountCondition... discountConditions) {
        this.title = title;
        this.duration = duration;
        this.fee = fee;
        this.discountConditions = Arrays.asList(discountConditions);
    }

    public Money calculateMovieFee(Screening screening) {
        if (isDiscountable(screening)) {
            return fee.minus(calculateDiscountAmount());
        }

        return fee;
    }

    private boolean isDiscountable(Screening screening) {
        return discountConditions.stream()
                .anyMatch(condition -> condition.isSatisfiedBy(screening));
    }

    abstract protected Money calculateDiscountAmount() ;

    protected Money getFee() {
        return fee;
    }
}
