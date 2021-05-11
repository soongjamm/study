package com.example.tobpring.chap03.v8seperateCallbackSql;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

public class UserDao {

    private JdbcContext jdbcContext;

    @Autowired
    public void setJdbcContext(DataSource dataSource) {
        this.jdbcContext = new JdbcContext();
        jdbcContext.setDataSource(dataSource);
    }

    public void deleteAll() {
        // 변하지 않는 부분을 따로 메소드로 추출했다.
        // 그런데 이 메소드는 다른 dao에서도 쓸 수 있는 내용이므로 JdbcContext로 빼자.
//        executeSql("delete from users");

        this.jdbcContext.executeSql("delete from users");
    }

    // context가 어떤 동작을 할지 dao에서 결정하고 있다.
//    private void executeSql(final String sql) {
//        jdbcContext.workingWithStrategy(new StatementStrategy() {
//            @Override
//            public PreparedStatement makeStrategy(Connection c) throws SQLException {
//                return c.prepareStatement(sql);
//            }
//        });
//    }

}
