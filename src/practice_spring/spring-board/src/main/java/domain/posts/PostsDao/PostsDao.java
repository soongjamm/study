package domain.posts.PostsDao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class PostsDao {

    private JdbcTemplate jdbcTemplate;

    public PostsDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
