package com.soongjamm.startboot.specification_pattern.eternity.domain;



import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Planet {
    private Atmosphere atmosphere;
    private List<Continent> continents;
    private List<Ocean> oceans;

    public Planet(Atmosphere atmosphere, List<Continent> continents, List<Ocean> oceans) {
        this.atmosphere = atmosphere;
        this.continents = continents;
        this.oceans = oceans;
    }

    public Collection<Object> getContinents() {
        return Collections.unmodifiableList(continents);
    }

    public Money getPrice() {
        return Money.wons(100);
    }
}
