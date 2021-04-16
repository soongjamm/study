<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.mysql.jdbc.Driver" %>
<%@ page import="java.io.Reader" %>
<%@ page import="java.io.IOException" %>

<%
    String memberID = request.getParameter("memberID");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>회원 정보</title>
</head>
<body>


<%
    // 1. JDBC 드라이버 로딩
    try {
        Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        System.out.println("class not found ..");
    }

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
        String jdbcDriver = "jdbc:mysql://localhost:3306/chap14?useUnicode=true&characterEncoding=utf8";
        String dbUser = "jspexam";
        String dbPassword = "Jsppw#1234";

        String query = "select * from MEMBER_history where memberid='" + memberID + "' order by memberid";

        // 2. 데이터베이스 커넥션 생성
        conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPassword);

        // 3. Statement 생성
        stmt = conn.createStatement();

        // 4. 쿼리 실행
        rs = stmt.executeQuery(query);

        // 5. 쿼리 실행 결과 출력
        boolean next = rs.next();
        if (next) {

%>

<table width="100%" border="1">
    <tr>
        <td>id</td>
        <td><%=memberID%>></td>
    </tr>
    <tr>
        <td>히스토리
        </td>
        <td>
            <%
                    String history = null;
                    Reader reader = null;
                    try {
                        reader = rs.getCharacterStream("history");
                        if (reader != null) {
                            StringBuilder sb = new StringBuilder();
                            char[] ch = new char[512];
                            int len = -1;

                            while ((len = reader.read(ch)) != -1) {
                                sb.append(ch, 0, len);
                                System.out.println(len + " len");
                            }

                            history = sb.toString();
                        }
                    } catch (IOException e) {
                        System.out.println("익셉션." + e.getMessage());
                    } finally {
                        if (reader != null) try {
                            reader.close();
                        } catch (IOException e) {
                        }
                    }%>
            <%=history%>
        </td>
    </tr>
</table>
<%} else {%>
<%=memberID%> 히스토리 엄슴 <%=next%>
<% }
} catch(SQLException e) { %>에러발생: <%=e.getMessage()%>
<% } finally {
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


</body>
</html>