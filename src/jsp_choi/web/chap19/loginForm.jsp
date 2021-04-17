<%--
  Created by IntelliJ IDEA.
  User: soongjamm
  Date: 2021/04/16
  Time: 9:47 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/chap19/login.jsp">
    아이디 <input type="text" name="memberId">
    암호 <input type="password" name="password">
    <input type="submit" value="login">
</form>

</body>
</html>
