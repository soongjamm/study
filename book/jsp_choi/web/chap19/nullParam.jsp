<%--
  Created by IntelliJ IDEA.
  User: soongjamm
  Date: 2021/04/16
  Time: 9:16 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NullParameterFilter test</title>
</head>
<body>
id param : <%=request.getParameter("id")%><br>
name param : <%=request.getParameter("name")%><br>
member param : <%=request.getParameter("member")%>
</body>
</html>
