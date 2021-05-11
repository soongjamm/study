package com.example.tobpring.chap03.v2trycatch;

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

    // 편의를 위해 deleteAll() 로 구현
    // 예외처리를 위해 try-catch를 작성하니 중복이 엄청나게 발생한다.
    // 변하는부분과 변하지 않는 부분을 구분. 쿼리부분만 변하는 부분이다.
    public void deleteAll() {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = connectionMaker.getConnection();

            ps = c.prepareStatement("select * from users where id = ?");

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
