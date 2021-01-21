package com.soongjamm.football_manager;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Player {
    private Long id;
    private String name;
    private String team;
    private Integer backNumber;
    private String country;
    private LocalDate birth;

    @Builder
    public Player(String name, Integer backNumber, String team, String country, LocalDate birth) {
        this.name = name;
        this.backNumber = backNumber;
        this.team = team;
        this.country = country;
        this.birth = birth;
    }

    public void moveTeam(String targetTeam) {
        this.backNumber = null;
        this.team = targetTeam;
    }
}
