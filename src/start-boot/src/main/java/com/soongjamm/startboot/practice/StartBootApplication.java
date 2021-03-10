package com.soongjamm.startboot.practice;

import com.soongjamm.startboot.practice.user.User;
import com.soongjamm.startboot.practice.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class StartBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartBootApplication.class, args);
    }

    @Autowired
    UserRepository userRepository;

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        userRepository.save(new User("kimstz0303@gmail.com", "p@ssw0rd", "soongjamm", LocalDateTime.now() ));

        return args -> {
            System.out.println("Let's inspect the beans provided by spring boot:");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for(String beanName : beanNames) {
                System.out.println(beanName);
            }
        };
    }

}
