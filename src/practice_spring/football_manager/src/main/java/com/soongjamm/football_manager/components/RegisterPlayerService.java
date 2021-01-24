package com.soongjamm.football_manager.components;

import com.soongjamm.football_manager.Player;
import com.soongjamm.football_manager.RegisterPlayerRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RegisterPlayerService {

    @Autowired
    private PlayerRepository playerRepository;

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
        System.out.println("선수 등록 완료");

    }
}
