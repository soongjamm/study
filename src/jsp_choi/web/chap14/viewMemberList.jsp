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
        // 1. JDBC 드라이버 로딩
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            System.out.println("class not found ..");
        }

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            String jdbcDriver = "jdbc:mysql://localhost:3306/chap14?useUnicode=true&characterEncoding=utf8";
            String dbUser = "jspexam";
            String dbPassword = "Jsppw#1234";

            String query = "select * from MEMBER order by MEMBERID";

            // 2. 데이터베이스 커넥션 생성
            conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPassword);

            // 3. Statement 생성
            stmt = conn.createStatement();

            // 4. 쿼리 실행
            rs = stmt.executeQuery(query);

            // 5. 쿼리 실행 결과 출력
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