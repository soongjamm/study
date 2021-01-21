package com.soongjamm.football_manager.config;

import com.soongjamm.football_manager.PlayerPrinter;
import com.soongjamm.football_manager.PlayerRepository;
import com.soongjamm.football_manager.RegisterPlayerService;
import com.soongjamm.football_manager.TransferService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCtx {

    @Bean
    public PlayerRepository playerRepository() {
        return new PlayerRepository();
    }

    @Bean
    public RegisterPlayerService registerPlayerService() {
        RegisterPlayerService svc = new RegisterPlayerService();
        svc.setPlayerRepository(playerRepository());
        return svc;
    }

    @Bean
    public TransferService transferService() {
        return new TransferService(playerRepository());
    }

    @Bean
    public PlayerPrinter playerPrinter() {
        PlayerPrinter printer = new PlayerPrinter();
        printer.setPlayerRepository(playerRepository());
        return printer;
    }

}
