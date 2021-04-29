package com.soongjamm.startboot.objects_practice.chapter5.data_reservationAgency_refactoring;

public class PeriodDiscountCondition implements DiscountCondition{
    private int sequence;

    public boolean isSatisfiedBy(Screening screening) {
        return sequence == screening.getSequence();
    }
}
