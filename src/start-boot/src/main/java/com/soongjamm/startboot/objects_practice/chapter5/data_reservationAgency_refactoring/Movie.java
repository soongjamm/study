package com.soongjamm.startboot.objects_practice.chapter5.data_reservationAgency_refactoring;

import com.soongjamm.startboot.objects_practice.chapter5.responsibility.Money;
import com.soongjamm.startboot.objects_practice.chapter5.responsibility.MovieType;

import java.util.Arrays;
import java.util.List;

public class Movie {

    List<DiscountCondition> discountConditions;
    private Money discountAmount;
    private double percent;
    private Money fee;
    private MovieType type;

    public Movie() {
    }

    public Movie(List<DiscountCondition> discountConditions, Money discountAmount, double percent, Money fee, MovieType type) {
        this.discountConditions = discountConditions;
        this.discountAmount = discountAmount;
        this.percent = percent;
        this.fee = fee;
        this.type = type;
    }

    public Money calculateMovieFee(Screening screening) {
        if (checkDiscountable(screening)) {
            return fee.minus(calculateDiscountedFee());
        }
        return fee;
    }

    public boolean checkDiscountable(Screening screening) {
        return discountConditions.stream()
                .anyMatch(condition -> condition.isSatisfiedBy(screening));
    }
    public Money calculateDiscountedFee() {
        switch (type) {
            case AMOUNT_DISCOUNT:
                return calculateAmountDiscountedFee();
            case PERCENT_DISCOUNT:
                return calculatePercentDiscountedFee();
        }
        throw new IllegalArgumentException() ;
    }

    private Money calculatePercentDiscountedFee() {
        return fee.times(percent);
    }

    private Money calculateAmountDiscountedFee() {
        return discountAmount;
    }
}
