package com.soongjamm.football_manager;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TransferService {

    @Autowired
    private PlayerRepository playerRepository;

    public TransferService() {
    }

    public void transfer(TransferRequestDto dto) {
        Optional<Player> player = playerRepository.findByName(dto.getPlayerName());

        Player targetPlayer = player.orElseThrow( () -> new IllegalArgumentException("존재하지 않는 선수입니다."));

        targetPlayer.moveTeam(dto.getHeadingTeamName());

        System.out.println("이적 완료");
    }
}
