package com.soongjamm.startboot.specification_pattern.eternity.speicification;


import com.soongjamm.startboot.specification_pattern.eternity.domain.Money;
import com.soongjamm.startboot.specification_pattern.eternity.domain.Planet;

public class PriceSpecification extends AbstractSpecification {
    private Money price;

    public PriceSpecification(Money price) {
        this.price = price;
    }

    @Override
    public boolean isSatisfied(Planet planet) {
        return price.isGreaterThanOrEqual(planet.getPrice());
    }
}
