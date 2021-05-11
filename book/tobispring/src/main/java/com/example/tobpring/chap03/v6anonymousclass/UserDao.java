package com.example.tobpring.chap03.v6anonymousclass;

import com.example.tobpring.chap01.v10부가기능확장.DaoFactory;
import com.example.tobpring.chap01.v1초난감dao.User;
import com.example.tobpring.chap03.v4strategyWithoutDI.StatementStrategy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 현재 구조
// Client - UserDao
// Context - UserDao#jdbcContext(StatementStrategy)
// Strategy - 각 메소드 내부에 anonymous class
// 지금 문제점 :  Context 는 모든 Dao가 공통적으로 사용할 수 있어야하는데, UserDao에 속해있다.
public class UserDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 전략패턴별로 클래스를 생성하지 않고 내부적으로 처리해준다.
    // 내부클래스
    public void deleteAll() {
        class DeleteAll implements StatementStrategy {
            @Override
            public PreparedStatement makeStrategy(Connection c) throws SQLException {
                return c.prepareStatement("select * from users where id = ?");
            }
        }
        StatementStrategy deleteAll = new DeleteAll();
        jdbcContext(deleteAll);
    }

    // 내부클래스에서 나아가 익명클래스
    public void add(final User user) {
        jdbcContext(new StatementStrategy() {
            @Override
            public PreparedStatement makeStrategy(Connection c) throws SQLException {
                PreparedStatement ps = c.prepareStatement("insert into users(id, password) values(?, ?) ");
                ps.setString(1, user.getId());
                ps.setString(2, user.getPassword());
                return ps;
            }
        });

        // lambda
        jdbcContext(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into users(id, password) values(?, ?) ");
            ps.setString(1, user.getId());
            ps.setString(2, user.getPassword());
            return ps;
        });
    }

    public void jdbcContext(StatementStrategy strategy) {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = dataSource.getConnection();

            ps = strategy.makeStrategy(c);
            ps.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

}
