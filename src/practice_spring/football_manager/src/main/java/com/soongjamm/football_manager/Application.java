package com.soongjamm.football_manager;

import com.soongjamm.football_manager.config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.time.Month;

public class Application {

    RegisterPlayerService registerPlayerService;
    TransferService transferService;
    PlayerPrinter playerPrinter;

    public void run () {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        registerPlayerService = ctx.getBean(RegisterPlayerService.class);
        transferService = ctx.getBean(TransferService.class);
        playerPrinter = ctx.getBean(PlayerPrinter.class);

        registerPlayerService.register(RegisterPlayerRequestDto.builder()
                .name("김승태")
                .backNumber(8)
                .birth(LocalDate.of(1994, Month.MARCH, 03))
                .team("FC벌")
                .build());
        transferService.transfer(TransferRequestDto.builder()
                .playerName("김승태")
                .headingTeamName("버거킹FC")
                .build());

        playerPrinter.print();

    }
}
