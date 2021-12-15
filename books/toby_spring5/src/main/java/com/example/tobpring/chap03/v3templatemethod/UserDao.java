package com.example.tobpring.chap03.v3templatemethod;

import com.example.tobpring.chap01.v10부가기능확장.DaoFactory;
import com.example.tobpring.chap01.v1초난감dao.User;
import com.example.tobpring.chap01.v6관계설정분리_주입.ConnectionMaker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class UserDao {

    private ConnectionMaker connectionMaker;

    public UserDao() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
        this.connectionMaker = ac.getBean("connectionMaker", ConnectionMaker.class);
    }

    // 템플릿메소드는 메소드별로 클래스가 하나씩 생긴다
    // 그리고 상속을 이용하므로 컴파일 시점에 확장구조가 결정되어있다.
    // --> 유연하지 못하다.
    public void deleteAll() {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = connectionMaker.getConnection();

            ps = makeStatement(c);
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

    protected abstract PreparedStatement makeStatement(Connection c) throws SQLException;

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
