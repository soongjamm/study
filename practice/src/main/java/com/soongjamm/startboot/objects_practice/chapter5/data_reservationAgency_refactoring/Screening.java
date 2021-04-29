package com.soongjamm.startboot.objects_practice.chapter5.data_reservationAgency_refactoring;

import com.soongjamm.startboot.objects_practice.chapter5.responsibility.Money;

public class Screening {
    Movie movie = new Movie();

    public Money calculateFee(int audienceCount) {
        return movie.calculateMovieFee(this).times(audienceCount);
    }

    public int getSequence() {
        return 0;
    }

}
