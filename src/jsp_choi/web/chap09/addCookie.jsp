<%@ page import="jakarta.servlet.http.Cookie" %><%--
  Created by IntelliJ IDEA.
  User: soongjamm
  Date: 2021/04/19
  Time: 9:23 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Cookie cookie = new Cookie("secure", "thisisvalue");
    Cookie cookie2 = new Cookie("httpOnlySecure", "hey");
    Cookie cookie3 = new Cookie("noFlag", "hi");
    cookie.setSecure(true);
    cookie2.setHttpOnly(true);
    cookie2.setSecure(true);
    response.addCookie(cookie);
    response.addCookie(cookie2);
    response.addCookie(cookie3);
%>

<html>
<head>
    <title>cookie factory</title>
</head>
<body>
<%=cookie.getName()%> : <%=cookie.getValue()%>
</body>
</html>
