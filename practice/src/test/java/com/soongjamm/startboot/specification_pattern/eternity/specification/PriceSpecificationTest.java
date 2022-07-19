package com.soongjamm.startboot.specification_pattern.eternity.specification;

import com.soongjamm.startboot.specification_pattern.eternity.domain.Money;
import com.soongjamm.startboot.specification_pattern.eternity.domain.Planet;
import com.soongjamm.startboot.specification_pattern.eternity.speicification.PriceSpecification;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PriceSpecificationTest extends AbstractPlanetTest{
    @Test
    void price() {
        Planet planet = createPlanet(Money.wons(5000), List.of(Money.wons(2000), Money.wons(3000)),  List.of(Money.wons(4000), Money.wons(5000)));

        PriceSpecification specification = new PriceSpecification(Money.wons(5000));

        assertTrue( specification.isSatisfied(planet) );
    }
}
