package com.example.tobpring.chap01.v3관심사분리_상속;

import com.example.tobpring.chap01.v1초난감dao.User;

import java.sql.*;

public abstract class UserDao {

    // 관심사의 분리 -- 상속 (템플릿메소드패턴, 팩토리메소드패턴)
    // 상위타입에서는 변하지 않는 기능만 정의하고, 변할 수 있는 것은 추상메소드를 사용했다. 구현은 하위에서. (템플릿 메소드 패턴)
    // 상위 타입에서는 생성을 추상적으로 정의하고 하위타입에서 생성방법을 정의했다.=> 오브젝트 생성방법을 로직에서 분리했다. (팩토리 메소드 패턴)
    protected abstract Connection getConnection() throws ClassNotFoundException, SQLException;

    // get, insert는 getConnection()으로 Connection 객체에만 의존할 뿐,
    // 구현체는 하위타입에서 구현했다. 어떤 구현체인지는 관심없다.
    // Connection의 단지 어떤 기능을 사용할 것인지에만 관심있다.
    // 그러나 상속을 이용했다.
    // -> 상-하위타입이 강하게 결합되어 상위클래스의 변경이 하위클래스에 어떤 영향을 끼칠지 예상이 어렵다.
    // -> 다중상속이 불가능하다. 미래의 상속 가능성을 막거나, 또는 이미 상속받은경우 사용할 수 없는 방법이다.
    // 그리고 커넥션을 얻는 메소드가 Dao안에 있기때문에, **아예 새로운 Dao가 생기면 getConnection()을 재활용 할 수가 없다.**
    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();
        PreparedStatement stmt = c.prepareStatement("select * from users where id = ?");
        stmt.setString(1, id);

        ResultSet rs = stmt.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("password"));

        // 연결 해제
        rs.close();
        stmt.close();
        c.close();

        return user;
    }

    public User insert(User user) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();
        PreparedStatement stmt = c.prepareStatement("insert into users(id, password) values (?, ?)");
        stmt.setString(1, user.getId());
        stmt.setString(2, user.getName());
        stmt.executeUpdate();

        stmt.close();
        c.close();
        return user;
    }

}
