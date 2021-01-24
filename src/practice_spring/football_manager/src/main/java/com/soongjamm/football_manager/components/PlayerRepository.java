package com.soongjamm.football_manager.components;

import com.soongjamm.football_manager.Player;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Getter
@Component
public class PlayerRepository {
    List<Player> players = new LinkedList<>();

    Optional<Player> findByName(String name) {
        return players.stream()
                .filter(x -> x.getName().equals(name))
                .findFirst();
    }

    public void add(Player player) {
        players.add(player);
    }
}
