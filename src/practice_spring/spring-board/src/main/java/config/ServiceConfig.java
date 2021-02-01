package config;

import domain.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.user.RegisterUserService;

@Configuration
public class ServiceConfig {

    @Autowired
    UserDao userDao;

    @Bean
    public RegisterUserService registerUserService() {
        RegisterUserService registerUserService = new RegisterUserService();
        registerUserService.setUserDao(userDao);
        return registerUserService;
    }
}
