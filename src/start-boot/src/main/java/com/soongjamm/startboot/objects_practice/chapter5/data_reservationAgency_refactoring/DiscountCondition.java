package com.soongjamm.startboot.objects_practice.chapter5.data_reservationAgency_refactoring;

public interface DiscountCondition {
     boolean isSatisfiedBy(Screening screening);
}
