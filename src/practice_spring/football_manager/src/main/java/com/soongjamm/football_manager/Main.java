package com.soongjamm.football_manager;

import com.soongjamm.football_manager.assembler.Assembler;

import java.time.LocalDate;
import java.time.Month;

public class Main {

    public static void main(String[] args) {

        Assembler assembler = new Assembler();
        RegisterPlayerService registerPlayerService = assembler.getRegisterPlayerService();
        TransferService transferService = assembler.getTransferService();


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

    }
}
