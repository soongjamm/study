<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: soongjamm
  Date: 2021/02/01
  Time: 4:12 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>약관 화면</title>
</head>
<body>
<form:form action="form">
    <label>약관에 동의하십니까? : </label>
    <input type="checkbox" name="agree" value="true">
    <input type="submit">
</form:form>
</body>
</html>
