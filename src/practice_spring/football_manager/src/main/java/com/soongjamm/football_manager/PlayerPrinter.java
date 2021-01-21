package com.soongjamm.football_manager;

import java.util.List;

public class PlayerPrinter {

    PlayerRepository playerRepository;

    public void setPlayerRepository(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void print() {
        List<Player> players = playerRepository.getPlayers();
        System.out.println("--선수 목록 출력--");
        players.forEach(x -> System.out.println(x.getName()));
    }

}
