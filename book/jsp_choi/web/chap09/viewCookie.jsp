<%@ page import="jakarta.servlet.http.Cookie" %><%--
  Created by IntelliJ IDEA.
  User: soongjamm
  Date: 2021/04/19
  Time: 9:23 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
        %>
<%=cookie.getName()%> : <%=cookie.getValue()%><br>
<%
    }
%>

<html>
<head>
    <title>cookie factory</title>
</head>
<body>
<script>
    let cookie = document.cookie;
    console.log(cookie);
</script>
</body>
</html>
