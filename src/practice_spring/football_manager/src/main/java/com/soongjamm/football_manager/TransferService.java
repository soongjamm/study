package com.soongjamm.football_manager;

import java.util.Optional;

public class TransferService {
    private PlayerRepository playerRepository;

    // 빈을 생성자로 주입받는다.
    public TransferService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void transfer(TransferRequestDto dto) {
        Optional<Player> player = playerRepository.findByName(dto.getPlayerName());

        Player targetPlayer = player.orElseThrow( () -> new IllegalArgumentException("존재하지 않는 선수입니다."));

        targetPlayer.moveTeam(dto.getHeadingTeamName());

    }
}
