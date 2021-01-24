package com.soongjamm.football_manager.config;

import com.soongjamm.football_manager.PlayerPrinter;
import com.soongjamm.football_manager.PlayerRepository;
import com.soongjamm.football_manager.RegisterPlayerService;
import com.soongjamm.football_manager.TransferService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Configuration
public class AppCtx {

    @Bean
    public PlayerRepository playerRepository() {
        return new PlayerRepository();
    }

    @Bean
    public RegisterPlayerService registerPlayerService() {
        RegisterPlayerService svc = new RegisterPlayerService();
        return svc;
    }

    @Bean
    public TransferService transferService() {
        return new TransferService();
    }

    @Bean
    public PlayerPrinter playerPrinter() {
        return new PlayerPrinter();
    }

    @Bean
    @Qualifier("localized-long")
    public DateTimeFormatter dateTimeFormatterLocalizedLong() {
        return DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
    }

    @Bean
    @Qualifier("localized-full")
    public DateTimeFormatter dateTimeFormatterLocalizedFull() {
        return DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
    }

}
