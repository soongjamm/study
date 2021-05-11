package com.example.tobpring.chap03.v7seperateContext_2;

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

// 1. JdbcContext를 빈으로 등록하지 않고 DataSource 수동 DI
// DataSource를 Dao에서 주입받고, 수동으로 Context 생성후 주입해주는 방법으로, DataSource를 한번쓰고 버릴수있다.
// 장점
// - Dao 내부에서 Context가 만드러지므로 외부에 관계가 드러나지 않는다.
// - 외부에 드러나지 않게 JdbcContext에 의존주입, 전략변경이 가능하다.
// 단점
// - Dao에 Context관련 코드가 추가된다
// - 여러 오브젝트가 사용해도 Context를 싱글톤으로 관리할 수 없다. (낭비)
public class UserDao {

    private JdbcContext jdbcContext;

    @Autowired
    public void setJdbcContext(DataSource dataSource) {
        this.jdbcContext = new JdbcContext();
        jdbcContext.setDataSource(dataSource);
    }

    // jdbcContext#workingWithStrategy(StatementStrategy)-템플릿, deleteAll-콜백 ====>> 템플릿콜백 패턴
    // 템플릿콜백의 특징
    // - 콜백메소드의 인자로 클라이언트의 컨텍스트 내부 정보를 받는다
    // - 콜백메소드가 자신을 생성한 클라이언트의 컨텍스트 내부 정보를 직접 참조한다
    // - 템플릿에 콜백 전달동시에 수동 DI가 일어난다.
    // - 클라이언트와 콜백이 강하게 결합된다.
    // - 일반적인 DI가 템플릿에 사용할 인자를 인스턴스로 받고 할당한다면, 템플릿콜백은 매번 메소드 단위로 새롭게 전달받는다.
    // 전략패턴으로 보기엔 좀 더 특별한 면이 있으므로 고유하게 기억하자.(inner class 활용.)
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
