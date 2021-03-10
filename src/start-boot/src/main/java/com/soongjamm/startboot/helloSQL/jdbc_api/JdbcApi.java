package com.soongjamm.startboot.helloSQL.jdbc_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class JdbcApi {

    @Autowired
    private DataSource dataSource;

    public void create() throws SQLException {
        System.out.println("DB TABLE CREATE GOGO");
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement("create table mytable (id int, name varchar(255))");
            stmt.execute();
            stmt = connection.prepareStatement("insert into mytable (id, name) values(?, ?)");
            stmt.setInt(1, 1);
            stmt.setString(2, "soongjamm");
            stmt.executeUpdate();

            connection.setAutoCommit(false);
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            // 자원반납
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
    }

    public void get() throws SQLException {
        System.out.println("GET DATA");
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement("select * from mytable where id=1");
            rs = stmt.executeQuery();
            if (rs.next()) {
                Person person = new Person(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                System.out.println(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
    }
}
