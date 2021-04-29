package com.soongjamm.startboot.objects_practice.chapter5.data_reservationAgency_refactoring;

import com.soongjamm.startboot.objects_practice.chapter5.responsibility.DiscountConditionType;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class SequenceDiscountCondition implements DiscountCondition{
    private int sequence;

    public boolean isSatisfiedBy(Screening screening) {
        return sequence == screening.getSequence();
    }
}
