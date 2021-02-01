package domain.user;

import dto.RegisterUserDto;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;

public class UserDao {

    DataSource ds;

    public UserDao(DataSource ds) {
        this.ds = ds;
    }

    public void insert(User user) {
        String query = "insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) VALUES (?, ?, ?, ?)";
        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setTimestamp(4, Timestamp.valueOf(user.getRegdate()));
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("에러 발생 에러 발생!");
            System.out.println(e.getMessage());
        }


    }

    public User findByEmail(String email) {
        String query = "select * from MEMBER where EMAIL = ?";
        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                User user = new User();
                user.setId(rs.getLong("ID"));
                user.setEmail(rs.getString("EMAIL"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setName(rs.getString("NAME"));
                user.setRegdate(rs.getTimestamp("REGDATE").toLocalDateTime());
                return user;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}
