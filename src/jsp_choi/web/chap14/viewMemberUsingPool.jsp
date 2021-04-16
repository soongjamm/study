<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.mysql.jdbc.Driver" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>회원 목록</title>
</head>
<body>

Member테이블의 내용
<table width="100%" border="1">
    <tr>
        <td>이름</td>
        <td>아이디</td>
        <td>이메일</td>
    </tr>
    <%
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            String jdbcDriver = "jdbc:apache:commons:dhcp:chap14";
            String query = "select * from MEMBER order by MEMBERID";

            conn = DriverManager.getConnection(jdbcDriver);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {

    %>
    <tr>
        <td><%= rs.getString("NAME")%>
        </td>
        <td><%= rs.getString("MEMBERID")%>
        </td>
        <td><%= rs.getString("EMAIL")%>
        </td>
    </tr>
    <%
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            // 6. 사용한 Statement 종료
            if (rs != null) try {
                rs.close();
            } catch (SQLException ex) {
            }
            if (stmt != null) try {
                stmt.close();
            } catch (SQLException ex) {
            }

            // 7. 커넥션 종료
            if (conn != null) try {
                conn.close();
            } catch (SQLException ex) {
            }
        }
    %>
</table>

</body>
</html>