package jdbc;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCPInit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        init();
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init connection pool");
        loadJDBCDriver();
        initConnectionPool();
    }

    private void initConnectionPool() {
        try {
            String jdbcUrl =
                    "jdbc:mysql://localhost:3306/chap14?useUnicode=true&CharacterEncoding=utf8";
            String username = "jspexam";
            String password = "Jsppw#1234";

            ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcUrl, username, password);
            PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connFactory, null);
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
            driver.registerPool("chap14", connectionPool);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadJDBCDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("fail to load JDBC DRiver", e);
        }
    }
}
