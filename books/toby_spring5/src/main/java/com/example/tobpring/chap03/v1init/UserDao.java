package com.example.tobpring.chap03.v1init;

import com.example.tobpring.chap01.v10부가기능확장.DaoFactory;
import com.example.tobpring.chap01.v1초난감dao.User;
import com.example.tobpring.chap01.v6관계설정분리_주입.ConnectionMaker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 현재 예외처리가 제대로 되어있지 않다.
// 만약 작업에 실패하면 자원이 반납되지 못하고 쌓여서 메모리 릭이 발생한다.
// 그래서 예외처리를 해준다. (엄청난 중복 발생)
public class UserDao {

    private ConnectionMaker connectionMaker;

    public UserDao() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
        this.connectionMaker = ac.getBean("connectionMaker", ConnectionMaker.class);
    }

    public User get(String id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        User user = new User();
        try {
            c = connectionMaker.getConnection();

            ps = c.prepareStatement("select * from users where id = ?");
            ps.setString(1, id);

            rs = ps.executeQuery();
            rs.next();
            user.setId(rs.getString("id"));
            user.setPassword(rs.getString("password"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
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

        return user;
    }

    public User insert(User user) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.getConnection();
        PreparedStatement stmt = c.prepareStatement("insert into users(id, password) values (?, ?)");
        stmt.setString(1, user.getId());
        stmt.setString(2, user.getPassword());
        stmt.executeUpdate();

        stmt.close();
        c.close();
        return user;
    }

}
