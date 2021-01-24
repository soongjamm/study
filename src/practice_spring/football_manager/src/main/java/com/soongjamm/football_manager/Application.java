package com.soongjamm.football_manager;

import com.soongjamm.football_manager.config.AppCtx;
import com.soongjamm.football_manager.components.RegisterPlayerService;
import com.soongjamm.football_manager.components.TransferService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Scanner;

public class Application {

    AnnotationConfigApplicationContext ctx;
    RegisterPlayerService registerPlayerService;
    TransferService transferService;
    Scanner scanner = new Scanner(System.in);

    public void run () {
        ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        boolean running = true;

        while(running) {
            System.out.println("register / transfer / exit");
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            if (input.startsWith("register ")) {
                commandRegisterPlayer();
            }
            if (input.startsWith("transfer ")) {
                commandTransfer();
            }
        }

        ctx.close();
    }

    private void commandTransfer() {
        transferService = ctx.getBean(TransferService.class);
        System.out.printf("이름 : ");
        String name = scanner.nextLine();
        System.out.printf("이적할 소속팀 이름 : ");
        String team = scanner.nextLine();

        transferService.transfer(TransferRequestDto.builder()
                .playerName(name)
                .headingTeamName(team)
                .build());

    }

    private void commandRegisterPlayer() {
        registerPlayerService = ctx.getBean(RegisterPlayerService.class);
        System.out.printf("이름 : ");
        String name = scanner.nextLine();
        System.out.printf("등번호 : ");
        int backNumber = Integer.parseInt(scanner.nextLine());
        System.out.printf("생일(yyyy-MM-dd) : ");
        int[] birth = Arrays.stream(scanner.nextLine()
                .split("-"))
                .mapToInt(x -> Integer.parseInt(x))
                .toArray();
        System.out.printf("소속팀 이름 : ");
        String team = scanner.nextLine();

        registerPlayerService.register(RegisterPlayerRequestDto.builder()
                .name(name)
                .backNumber(backNumber)
                .birth(LocalDate.of(birth[0], Month.of(birth[1]), birth[2]))
                .team(team)
                .build());

    }
}
