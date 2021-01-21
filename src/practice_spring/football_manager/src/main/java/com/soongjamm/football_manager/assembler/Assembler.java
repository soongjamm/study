package com.soongjamm.football_manager.assembler;

import com.soongjamm.football_manager.PlayerRepository;
import com.soongjamm.football_manager.RegisterPlayerService;
import com.soongjamm.football_manager.TransferService;
import lombok.Getter;

@Getter
public class Assembler {

    private PlayerRepository playerRepository;
    private RegisterPlayerService registerPlayerService;
    private TransferService transferService;

    public Assembler() {
        playerRepository = new PlayerRepository();
        registerPlayerService = new RegisterPlayerService();
        registerPlayerService.setPlayerRepository(playerRepository);
        transferService = new TransferService(playerRepository);
    }


}
