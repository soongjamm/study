package config;

import domain.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DaoConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    public UserDao userDao() {
        return new UserDao(dataSource);
    }
}
