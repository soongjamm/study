package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/spring5board?characterEncoding=utf8&serverTimezone=UTC");
        ds.setUsername("soongjamm");
        ds.setPassword("S00ngj@mm");
        ds.setInitialSize(10);
        ds.setMaxActive(2);
        ds.setTestWhileIdle(true);
        ds.setMinEvictableIdleTimeMillis(1000 * 60 * 3);
        ds.setTimeBetweenEvictionRunsMillis(1000 * 10);
        return ds;
    }
}
