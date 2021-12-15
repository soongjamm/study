<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.mysql.jdbc.Driver" %>


<%
  request.setCharacterEncoding("utf-8");

  String memberID = request.getParameter("memberID");
  String name = request.getParameter("name");

  int updateCount = 0;

  Class.forName("com.mysql.jdbc.Driver");

  Connection conn = null;
  Statement stmt = null;

  try {
      String jdbcDriver = "jdbc:mysql://localhost:3306/chap14?useUnicode=true&characterEncoding=utf8";
      String dbUser = "jspexam";
      String dbPassword = "Jsppw#1234";

      //              update MEMBER set NAME=XX where MEMBERID=XX;
      String query = "update MEMBER set NAME='" + name + "' " + "where MEMBERID='" + memberID+"'";

      conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPassword);
      stmt = conn.createStatement();
      updateCount = stmt.executeUpdate(query);
  } finally {
      if(stmt != null) try {stmt.close();} catch(SQLException e){}
      if(conn != null) try {conn.close();} catch(SQLException e){}
  }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>이름 변경</title>
</head>
<body>
<%
    if (updateCount > 0) { %>
    <%=memberID%>의 이름을 <%=name%> (으)로 변경
<%} else {%>
<%=memberID%> 아이디 존재 안함
<% } %>
</body>
</html>