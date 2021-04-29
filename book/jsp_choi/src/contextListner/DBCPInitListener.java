package contextListner;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // properties형식으로 작성된 poolConfig 읽어서 Properties로 변환
        String poolConfig = sce.getServletContext().getInitParameter("poolConfig");
        Properties prop = new Properties();
        try {
            prop.load(new StringReader(poolConfig));
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadJDBCDriver(prop);
        initConnectionPool(prop);
    }

    private void loadJDBCDriver(Properties prop) {
        String driverClass = prop.getProperty("jdbcDriver");
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initConnectionPool(Properties prop) {
        try {
            String url = prop.getProperty("jdbcUrl");
            String user = prop.getProperty("dbUser");
            String pw = prop.getProperty("dbPw");

            ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(url, user, pw);
            PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
            poolableConnectionFactory.setValidationQuery("select 1");
            GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
            poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
            poolConfig.setTestWhileIdle(true);
            poolConfig.setMinIdle(4);
            poolConfig.setMaxTotal(50);

            GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory, poolConfig);
            poolableConnectionFactory.setPool(connectionPool);

            Class.forName("org.apache.commons.dbcp2.PoolingDriver");
            PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
            String poolName = prop.getProperty("poolName");
            driver.registerPool(poolName, connectionPool);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
