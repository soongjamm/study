package com.soongjamm.football_manager.config;

import com.soongjamm.football_manager.DB.DatabaseConnection;
import com.soongjamm.football_manager.DB.DatabaseConnection2;
import com.soongjamm.football_manager.aop.PrintPlayerListAspect;
import com.soongjamm.football_manager.components.RegisterPlayerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@EnableAspectJAutoProxy
@Configuration
@ComponentScan(basePackages = {"com.soongjamm.football_manager.components", "com.soongjamm.football_manager.DB"},
        excludeFilters = @Filter(type = FilterType.ASPECTJ, pattern = "com.soongjamm.football_manager.config"))
public class AppCtx {

    @Bean
    public PrintPlayerListAspect printPlayerListAspect() {
        return new PrintPlayerListAspect();
    }

    @Bean
    public RegisterPlayerService registerPlayerService() {
        return new RegisterPlayerService();
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

    @Bean(initMethod = "sayHi", destroyMethod = "sayBye")
    public DatabaseConnection2 databaseConnection2() {
        return new DatabaseConnection2();
    }

}
