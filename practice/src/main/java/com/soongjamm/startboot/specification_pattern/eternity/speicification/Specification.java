package com.soongjamm.startboot.specification_pattern.eternity.speicification;

import com.soongjamm.startboot.specification_pattern.eternity.domain.Planet;

public interface Specification {
    boolean isSatisfied(Planet planet);
    Specification and(Specification other);
}
