package com.example.tobpring.chap03.v4strategyWithoutDI;

import com.example.tobpring.chap01.v10부가기능확장.DaoFactory;
import com.example.tobpring.chap01.v1초난감dao.User;
import com.example.tobpring.chap01.v6관계설정분리_주입.ConnectionMaker;
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

    public void deleteAll() {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = connectionMaker.getConnection();

            // 의존관계 설정을 UserDao 내부에서 하기때문에 변경에 유연하지 못하다.
            // == 전략패턴은 필요에따라 전략을 바꿀수 있어야하는데 이미 결정되어있다.
            // 어떤 전략을 사용할지는 클라이언트가 결정하도록 바깥으로 빼주는게 좋다.
            StatementStrategy strategy = new DeleteAllStrategy();
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

    public void add(User user) {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = connectionMaker.getConnection();

            ps = c.prepareStatement("insert into users(id, password) values(?, ?) ");
            ps.setString(1, user.getId());
            ps.setString(2, user.getPassword());

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
