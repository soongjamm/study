package servlet;

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

public class DBCPInit2 extends HttpServlet {

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
            String jdbcUrl = getInitParameter("jdbcUrl");
            String username = getInitParameter("dbUser");
            String password = getInitParameter("dbPw");

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
            driver.registerPool("poolName", connectionPool);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadJDBCDriver() {
        String driverClass = getInitParameter("jdbcdriver"); // getInitParameter는 HttpServlet의 부모인 GenericServlet의 메소드
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("fail to load JDBC DRiver", e);
        }
    }
}
