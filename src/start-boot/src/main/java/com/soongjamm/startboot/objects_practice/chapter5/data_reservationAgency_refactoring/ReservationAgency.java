package com.soongjamm.startboot.objects_practice.chapter5.data_reservationAgency_refactoring;

import com.soongjamm.startboot.objects_practice.chapter2_completedVersion.Customer;
import com.soongjamm.startboot.objects_practice.chapter2_completedVersion.Reservation;
import com.soongjamm.startboot.objects_practice.chapter5.responsibility.DiscountConditionType;
import com.soongjamm.startboot.objects_practice.chapter5.responsibility.Money;

public class ReservationAgency {
    public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
        Money fee = screening.calculateFee(audienceCount);
        return createReservation(screening, customer, audienceCount, fee);
    }

    private Reservation createReservation(Screening screening, Customer customer, int audienceCount, Money fee) {
        return new Reservation();
    }
}
