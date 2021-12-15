package com.example.tobpring.chap03.v5strategyWithDI;

import com.example.tobpring.chap01.v10부가기능확장.DaoFactory;
import com.example.tobpring.chap01.v1초난감dao.User;
import com.example.tobpring.chap01.v6관계설정분리_주입.ConnectionMaker;
import com.example.tobpring.chap03.v4strategyWithoutDI.DeleteAllStrategy;
import com.example.tobpring.chap03.v4strategyWithoutDI.StatementStrategy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

    private ConnectionMaker connectionMaker;

    public UserDao() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
        this.connectionMaker = ac.getBean("connectionMaker", ConnectionMaker.class);
    }

    // 각각의 메소드는 전략을 생성하고 컨텍스트에 처리를 맡긴다.
    // 클라이언트와 컨텍스트가 구분되지 않았지만 DI가 되었다.
    public void deleteAll() {
        StatementStrategy strategy = new DeleteAllStrategy();
        jdbcContext(strategy);
    }

    // user는 클라이언트로부터 주입받고, 전략의 생성자로 넘겨준다.
    public void add(User user) {
        StatementStrategy strategy = new AddStrategy(user);
        jdbcContext(strategy);
    }

    public void jdbcContext(StatementStrategy strategy) {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = connectionMaker.getConnection();

            ps = strategy.makeStrategy(c);
            ps.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
