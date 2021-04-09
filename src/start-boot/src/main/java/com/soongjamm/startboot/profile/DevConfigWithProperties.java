package com.soongjamm.startboot.profile;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@Profile("xx")
public class DevConfigWithProperties {

    @Value("${application-test.driver}")
    private String driver;
    @Value("${application-test.user}")
    private String user;
    @Value("${application-test.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        ds.setUrl(driver);
        ds.setUsername(user);
        ds.setPassword(password);
        System.out.println("!!!+=====!!!+=====!!!+=====!!!+=====!!!+=====!!!+=====!!!+=====");
        return ds;
    }
}
