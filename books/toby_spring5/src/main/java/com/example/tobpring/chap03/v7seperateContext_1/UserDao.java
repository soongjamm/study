package com.example.tobpring.chap03.v7seperateContext_1;

import com.example.tobpring.chap01.v1초난감dao.User;
import com.example.tobpring.chap03.v4strategyWithoutDI.StatementStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
// 클라이언트와 컨텍스트를 분리할건데, JdbcContext를 인터페이스로 두지 않고 구체클래스로 둘것이다.
// 각 방법에 대해 구체적인 사용이유를 설명할 수 없다면 그냥 인터페이스를 중간에 두고 인터페이스를 DI해라.

// UserDao가 구체클래스를 의존하면서 강하게 결합되었지만,
// JdbcContext 는 변하지 않는 코드다. 만약 hibernate같은 orm을 쓰면 어차피 바뀌게 되는 코드다.
// 그래서 결합클래스에 의존하게 두어도 큰 상관없다.

// 1. JdbcContext를 빈으로 등록하는 방법.
// 빈으로 등록해야할 이유
// - 반드시 JdbcContext가 빈으로 등록되어야 DataSource를 주입받을 수 있다.
// - 상태정보를 가지고 있지 않고, 하나의 서비스 객체이므로 여러 오브젝트에서 공유하는게 이상적이다.
// 단점
// - (web.xml) 설정에 Dao와 구체클래스 JdbcContext 의존정보가 드러난다.
public class UserDao {

    private JdbcContext jdbcContext;

    @Autowired
    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public void deleteAll() {
        class DeleteAll implements StatementStrategy {
            @Override
            public PreparedStatement makeStrategy(Connection c) throws SQLException {
                return c.prepareStatement("select * from users where id = ?");
            }
        }
        StatementStrategy deleteAll = new DeleteAll();
        jdbcContext.workingWithStrategy(deleteAll);
    }

    public void add(final User user) {
        jdbcContext.workingWithStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makeStrategy(Connection c) throws SQLException {
                PreparedStatement ps = c.prepareStatement("insert into users(id, password) values(?, ?) ");
                ps.setString(1, user.getId());
                ps.setString(2, user.getPassword());
                return ps;
            }
        });
    }

}
