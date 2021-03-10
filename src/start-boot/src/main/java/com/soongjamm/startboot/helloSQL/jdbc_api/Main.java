package com.soongjamm.startboot.helloSQL.jdbc_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

    @Autowired
    private JdbcApi jdbcApi;

    @Autowired
    private UsingJdbcTemplate usingJdbcTemplate;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Main.class);
        app.run(args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
//            usingJdbcTemplate.create();
            jdbcApi.create();
//            jdbcApi.get();
            usingJdbcTemplate.get();
        };

    }
}
