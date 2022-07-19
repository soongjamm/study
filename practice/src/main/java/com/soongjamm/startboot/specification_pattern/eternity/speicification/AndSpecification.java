package com.soongjamm.startboot.specification_pattern.eternity.speicification;

import com.soongjamm.startboot.specification_pattern.eternity.domain.Planet;

// AndSpecification 은 두개의 Specification 을 공통의 인터페이스로 캡슐화하는 Composite 다.
public class AndSpecification extends AbstractSpecification {
    private Specification one;
    private Specification other;

    public AndSpecification(Specification one, Specification other) {
        this.one = one;
        this.other = other;
    }

    @Override
    public boolean isSatisfied(Planet planet) {
        return one.isSatisfied(planet) && other.isSatisfied(planet);
    }
}
