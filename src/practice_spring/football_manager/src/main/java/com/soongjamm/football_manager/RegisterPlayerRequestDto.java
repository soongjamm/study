package com.soongjamm.football_manager;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RegisterPlayerRequestDto {
    private String name;
    private String team;
    private Integer backNumber;
    private String country;
    private LocalDate birth;

    @Builder
    public RegisterPlayerRequestDto(String name, String team, Integer backNumber, String country, LocalDate birth) {
        this.name = name;
        this.team = team;
        this.backNumber = backNumber;
        this.country = country;
        this.birth = birth;
    }

}
