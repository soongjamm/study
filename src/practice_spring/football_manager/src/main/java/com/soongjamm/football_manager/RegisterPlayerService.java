package com.soongjamm.football_manager;

import java.util.Optional;

public class RegisterPlayerService {
    private PlayerRepository playerRepository;

    // 빈을 세터로 주입받는다.
    public void setPlayerRepository(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void register(RegisterPlayerRequestDto dto) {

        Optional<Player> player = playerRepository.findByName(dto.getName());
        // 같은 이름의 선수가 있으면 예외 발생
        player.ifPresent( x -> {
            throw new IllegalArgumentException(x.getName()+ " alrady exists.");
        });

        // 같은 이름의 선수가 없으면 DB에 삽입
        Player newPlayer =  Player.builder()
                .name(dto.getName())
                .team(dto.getTeam())
                .country(dto.getCountry())
                .backNumber(dto.getBackNumber())
                .birth(dto.getBirth())
                .build();
        playerRepository.add(newPlayer);
    }
}
