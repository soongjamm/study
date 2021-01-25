package chap08.dbquery;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 스프링(JdbcTemplate)을 사용하지 않았을 때 사용 예.
 * Connection, DataResource, Statement, ResultSet을 직접 사용해줘야 한다.
 * (dataSource는 DbConfig에서 설정한 빈을 주입받는다.)
 */

public class DbQueryWithoutSpring {
    private DataSource dataSource;

    public DbQueryWithoutSpring(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int count() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection(); // 풀에서 구함. 객체를 생성하는게 아니라 그냥 사용하는거니까 되는거임. (실제론 구현 클래스 주입받으면 되니까)
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("select count(*) from MEMBER")) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close(); // 실제론    커넥션을 끊지 않고, 풀에 반환
                } catch (SQLException e) {
                }
            }
        }
    }
}
