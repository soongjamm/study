package chap08.spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class MemberDao {

    private JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Member selectByEmail(String email) {
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER where EMAIL =?",
                (rs, row) -> { // 만약, 자주 사용될 것 가타면 따로 구현크래스를 만들어도 된다. new MEmberRowMapper() * MemberRowMapper
                    Member member = new Member(
                            rs.getString("EMAIL"),
                            rs.getString("PASSWORD"),
                            rs.getString("NAME"),
                            rs.getTimestamp("REGDATE").toLocalDateTime()
                    );
                    member.setId(rs.getLong("ID"));
                    return member;
                },
                email);

        return results.isEmpty() ? null : results.get(0);
    }

    public void insert(Member member) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement pstmt = con.prepareStatement(
                    "insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE)" + "values (?, ?, ?, ?)",
                    new String[]{"ID"});
            pstmt.setString(1, member.getEmail());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());
            pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
            return pstmt;
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        member.setId(keyValue.longValue());
    }

    public void update(Member member) {
        jdbcTemplate.update(
                "update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?",
                member.getName(), member.getPassword(), member.getEmail());
    }

    public List<Member> selectAll() {
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER",
                new MemberRowMapper()
        );

        return results.isEmpty() ? null : results;
    }

    public int count() {
        Integer count = jdbcTemplate.queryForObject(
                "select count(*) from MEMBER", Integer.class
        );

        return count;

//        List<Integer> count = jdbcTemplate.query(
//                "select count(*) from MEMBER",
//                new RowMapper<Integer>() {
//                    @Override
//                    public Integer mapRow(ResultSet rs, int row) throws SQLException {
//                        return rs.getInt(1);
//                    }
//                }
//        );
//        return count.get(0);
    }

}
