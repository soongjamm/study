package com.soongjamm.football_manager;

import com.soongjamm.football_manager.config.AppCtx;
import com.soongjamm.football_manager.components.PlayerPrinter;
import com.soongjamm.football_manager.components.PlayerRepository;
import com.soongjamm.football_manager.components.RegisterPlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


class RegisterPlayerServiceTest {

    AnnotationConfigApplicationContext ctx = null; // 스프링 컨테이너 설정 클래스
    PlayerRepository playerRepository;
    RegisterPlayerService registerPlayerService;
    PlayerPrinter playerPrinter;

    @Disabled
    @BeforeEach
    void getBean() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class); // 스프링 컨테이너 생성
        playerRepository = ctx.getBean(PlayerRepository.class);
        System.out.println(playerRepository.toString());
        registerPlayerService = ctx.getBean(RegisterPlayerService.class);
        playerPrinter = ctx.getBean(PlayerPrinter.class);
    }

    @Test
    void testRegisterPlayer () {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class); // 스프링 컨테이너 생성
        playerRepository = ctx.getBean("playerRepository", PlayerRepository.class);
        System.out.println("테스트클래스 안에있는 : "+playerRepository.toString());
        registerPlayerService = ctx.getBean("registerPlayerService", RegisterPlayerService.class);
        playerPrinter = ctx.getBean("playerPrinter", PlayerPrinter.class);

        String name = "김승태";
        String country = "대한민국";
        Integer backNumber = 8;
        LocalDate birth = LocalDate.of(1994, Month.MARCH, 03);

        RegisterPlayerRequestDto dto = RegisterPlayerRequestDto.builder()
                .name(name)
                .country(country)
                .backNumber(backNumber)
                .birth(birth)
                .build();
        registerPlayerService.register(dto);
//
//        Optional<Player> p = playerRepository.findByName(name);
//        Player player = p.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 플레이어입니다."));
//        assertThat(player.getName()).isEqualTo(name);
//        assertThat(player.getCountry()).isEqualTo(country);
//        assertThat(player.getBackNumber()).isEqualTo(backNumber);
//
//        playerPrinter.print();
    }

}