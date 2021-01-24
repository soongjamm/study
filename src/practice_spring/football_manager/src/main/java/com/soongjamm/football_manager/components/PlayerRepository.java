package com.soongjamm.football_manager.components;

import com.soongjamm.football_manager.Player;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Component
public class PlayerRepository {
    List<Player> players = new LinkedList<>();

    Optional<Player> findOneByName(String name) {
        return players.stream()
                .filter(x -> x.getName().equals(name))
                .findFirst();
    }

    public void add(Player player) {
        players.add(player);
    }

    public List<Player> findAllByName(String name) {
        return players.stream()
                .filter(x -> x.getName().equals(name))
                .collect(Collectors.toList());
    }
}
