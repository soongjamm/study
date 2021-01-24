package com.soongjamm.football_manager.components;

import com.soongjamm.football_manager.Player;
import com.soongjamm.football_manager.RegisterPlayerRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RegisterPlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public void register(RegisterPlayerRequestDto dto) {

        List<Player> players = playerRepository.findAllByName(dto.getName());
        long countDuplicate = players.stream()
                .filter(x -> x.getName().equals(dto.getName()))
                .count();

        String name = dto.getName();
        if (countDuplicate > 0) {
            name += " " + countDuplicate;
        }

        // 같은 이름의 선수가 없으면 DB에 삽입
        Player newPlayer =  Player.builder()
                .name(name)
                .team(dto.getTeam())
                .country(dto.getCountry())
                .backNumber(dto.getBackNumber())
                .birth(dto.getBirth())
                .build();
        playerRepository.add(newPlayer);
        System.out.println("선수 등록 완료!");

    }
}
