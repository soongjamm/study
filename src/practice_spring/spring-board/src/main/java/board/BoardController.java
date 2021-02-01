package board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
public class BoardController {

    DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    class NameCommand {
        String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    @GetMapping
    public String index(Model model) {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) values('AAA@AAA.COM', '1234ASDF', 'SOONG', '20200101')");
            ResultSet rs = stmt.executeQuery("select * from MEMBER;");
            rs.next();
            String name = rs.getString("NAME");
            System.out.println(name);
            model.addAttribute("name", name);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


        return "index";
    }
}
