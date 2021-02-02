package domain.user;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class UserDao {

    JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(User user) {
        String query = "insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, user.getEmail(), user.getPassword(), user.getName(), user.getRegdate());
    }

    public User findByEmail(String email) {
        String query = "select * from MEMBER where EMAIL = ?";
        List<User> result = jdbcTemplate.query(query, (rs, row) -> {
            User user = new User();
            user.setId(rs.getLong("ID"));
            user.setEmail(rs.getString("EMAIL"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setName(rs.getString("NAME"));
            user.setRegdate(rs.getTimestamp("REGDATE").toLocalDateTime());
            return user;
        }, email);
        return result.isEmpty() ? null : result.get(0);
    }

}
