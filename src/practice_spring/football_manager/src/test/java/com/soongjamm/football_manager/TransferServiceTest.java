package com.soongjamm.football_manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TransferServiceTest {

    PlayerRepository playerRepository;
    RegisterPlayerService registerPlayerService;
    TransferService transferService;

    @Test
    void testTransfer() {
        String name = "김승태";
        String country = "대한민국";
        Integer backNumber = 8;
        LocalDate birth = LocalDate.of(1994, Month.MARCH, 03);
        String headingTeam = "FC버거킹";

        RegisterPlayerRequestDto registerDto = RegisterPlayerRequestDto.builder()
                .name(name)
                .country(country)
                .backNumber(backNumber)
                .birth(birth)
                .build();
        registerPlayerService.register(registerDto);


        TransferRequestDto transferDto = TransferRequestDto.builder()
                .playerName(name)
                .headingTeamName(headingTeam)
                .build();
        transferService.transfer(transferDto);

        Optional<Player> p = playerRepository.findByName(name);
        Player player = p.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 플레이어입니다."));

        assertThat(player.getTeam()).isEqualTo(headingTeam);
    }
}