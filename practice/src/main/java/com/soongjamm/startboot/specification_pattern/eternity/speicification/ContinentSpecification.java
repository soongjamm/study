package com.soongjamm.startboot.specification_pattern.eternity.speicification;

import com.soongjamm.startboot.specification_pattern.eternity.domain.Planet;

public class ContinentSpecification extends AbstractSpecification {
    private int continentSize;

    public ContinentSpecification(int continentSize) {
        this.continentSize = continentSize;
    }

    @Override
    public boolean isSatisfied(Planet planet) {
        return planet.getContinents().size() == continentSize;
    }
}
