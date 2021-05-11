package com.example.tobpring.chap01.v9의존관계검색;

import com.example.tobpring.chap01.v1초난감dao.User;
import com.example.tobpring.chap01.v6관계설정분리_주입.ConnectionMaker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private ConnectionMaker connectionMaker;

    // 의존성 검색 - 이것도 IoC를 만족한다.
    // UserDao가 직접 의존관계 오브젝트를 선택하지 않고 런타임에 AC에 의해 의존관계가 만들어진다.
    // 의존성 주입 DI만이 IoC를 만족하는 방법이 아니란 것을 보여준다.
    // 그러나 로직과 관련없는 스프링코드가 들어오게 되니 의존성주입보다 좋진 못하다.
    public UserDao() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
        this.connectionMaker = ac.getBean("connectionMaker", ConnectionMaker.class);
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.getConnection();
        PreparedStatement stmt = c.prepareStatement("select * from users where id = ?");
        stmt.setString(1, id);

        ResultSet rs = stmt.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setPassword(rs.getString("password"));

        // 연결 해제
        rs.close();
        stmt.close();
        c.close();

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
