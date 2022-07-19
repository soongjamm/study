package com.soongjamm.startboot.specification_pattern.eternity;



import com.soongjamm.startboot.specification_pattern.eternity.domain.Atmosphere;
import com.soongjamm.startboot.specification_pattern.eternity.domain.Continent;
import com.soongjamm.startboot.specification_pattern.eternity.domain.Money;
import com.soongjamm.startboot.specification_pattern.eternity.domain.Ocean;
import com.soongjamm.startboot.specification_pattern.eternity.domain.Planet;
import com.soongjamm.startboot.specification_pattern.eternity.domain.Ratio;
import com.soongjamm.startboot.specification_pattern.eternity.speicification.ContinentSpecification;
import com.soongjamm.startboot.specification_pattern.eternity.speicification.PriceSpecification;
import com.soongjamm.startboot.specification_pattern.eternity.speicification.Specification;

import java.util.List;

import static com.soongjamm.startboot.specification_pattern.eternity.domain.Atmosphere.*;

public class Main {
    public static void main(String[] args) {
        Planet planet = new Planet(new Atmosphere(Money.wons(5000),
                element("N", Ratio.of(0.8)),
                element("O", Ratio.of(0.2))
        ),
                List.of(new Continent("아시아", Money.wons(1000)), new Continent("유럽", Money.wons(1000))),
                List.of(new Ocean("아시아", Money.wons(4000)), new Ocean("유럽", Money.wons(4000))));

        Specification specification = new ContinentSpecification(8).and(new PriceSpecification(Money.wons(100)));
        boolean satisfied = specification.isSatisfied(planet);

        System.out.println(satisfied);
    }
}
