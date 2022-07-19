package com.soongjamm.startboot.specification_pattern.eternity.specification;

import com.soongjamm.startboot.specification_pattern.eternity.domain.Atmosphere;
import com.soongjamm.startboot.specification_pattern.eternity.domain.Continent;
import com.soongjamm.startboot.specification_pattern.eternity.domain.Money;
import com.soongjamm.startboot.specification_pattern.eternity.domain.Ocean;
import com.soongjamm.startboot.specification_pattern.eternity.domain.Planet;
import com.soongjamm.startboot.specification_pattern.eternity.domain.Ratio;

import java.util.ArrayList;
import java.util.List;

import static com.soongjamm.startboot.specification_pattern.eternity.domain.Atmosphere.*;

public class AbstractPlanetTest {

    protected Planet createPlanet(Continent... continents) {
        return new Planet(
                new Atmosphere(Money.wons(5000),
                        element("N", Ratio.of(0.8)),
                        element("O", Ratio.of(0.2))
                ),
                List.of(continents),
                List.of(new Ocean("아시아", Money.wons(4000)), new Ocean("유럽", Money.wons(4000)))
        );
    }


    protected Planet createPlanet(Money atmospherePrice, List<Money> nationsPrice, List<Money> oceansPrice) {
        return new Planet(
                createAtmosphere(atmospherePrice),
                createContinent(nationsPrice),
                createOceans(oceansPrice)
        );
    }

    private Atmosphere createAtmosphere(Money atmospherePrice) {
        return new Atmosphere(atmospherePrice,
                element("N", Ratio.of(0.8)),
                element("O", Ratio.of(0.2))
        );
    }

    private List<Continent> createContinent(List<Money> nationsPrice) {
        List<Continent> result = new ArrayList<>();
        for (Money each : nationsPrice) {
            result.add(new Continent("대륙", each));
        }
        return result;
    }

    private List<Ocean> createOceans(List<Money> oceansPrice) {
        List<Ocean> result = new ArrayList<>();
        for (Money each : oceansPrice) {
            result.add(new Ocean("대양", each));
        }
        return result;
    }
}
