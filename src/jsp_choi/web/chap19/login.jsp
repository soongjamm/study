<%--
  Created by IntelliJ IDEA.
  User: soongjamm
  Date: 2021/04/16
  Time: 9:47 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String memberId = request.getParameter("memberId");
    session.setAttribute("MEMBERID", memberId);
%>
<html>
<head>
    <title>login</title>
</head>
<body>
login success.

</body>
</html>
