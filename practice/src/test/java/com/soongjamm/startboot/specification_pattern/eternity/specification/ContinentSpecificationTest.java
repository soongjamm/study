package com.soongjamm.startboot.specification_pattern.eternity.specification;

import com.soongjamm.startboot.specification_pattern.eternity.domain.Continent;
import com.soongjamm.startboot.specification_pattern.eternity.domain.Money;
import com.soongjamm.startboot.specification_pattern.eternity.domain.Planet;
import com.soongjamm.startboot.specification_pattern.eternity.speicification.ContinentSpecification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContinentSpecificationTest extends AbstractPlanetTest {

    @Test
    void continentSize() {
        Planet planet = createPlanet(new Continent("아시아", Money.wons(1000)), new Continent("유럽", Money.wons(1000)));

        ContinentSpecification specification = new ContinentSpecification(2);

        assertTrue( specification.isSatisfied(planet) );
    }
}