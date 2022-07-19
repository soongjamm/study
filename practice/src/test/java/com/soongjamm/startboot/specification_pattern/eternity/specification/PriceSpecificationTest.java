package com.soongjamm.startboot.specification_pattern.eternity.specification;

import com.soongjamm.startboot.specification_pattern.eternity.domain.Atmosphere;
import com.soongjamm.startboot.specification_pattern.eternity.domain.Continent;
import com.soongjamm.startboot.specification_pattern.eternity.domain.Money;
import com.soongjamm.startboot.specification_pattern.eternity.domain.Ocean;
import com.soongjamm.startboot.specification_pattern.eternity.domain.Planet;
import com.soongjamm.startboot.specification_pattern.eternity.domain.Ratio;
import com.soongjamm.startboot.specification_pattern.eternity.speicification.PriceSpecification;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.soongjamm.startboot.specification_pattern.eternity.domain.Atmosphere.*;
import static org.junit.jupiter.api.Assertions.*;

class PriceSpecificationTest {
    @Test
    void price() {
        Planet planet = new Planet(
                new Atmosphere(Money.wons(5000),
                        element("N", Ratio.of(0.8)),
                        element("O", Ratio.of(0.2))
                ),
                List.of(new Continent("아시아"), new Continent("유럽")),
                List.of(new Ocean("아시아"), new Ocean("유럽"))
        );

        PriceSpecification specification = new PriceSpecification(Money.wons(5000));

        assertTrue( specification.isSatisfied(planet) );
    }
}