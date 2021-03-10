<%--
  Created by IntelliJ IDEA.
  User: soongjamm
  Date: 2021/02/13
  Time: 10:50 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>인덱스 페이지</title>
</head>
<body>
<h1>nothing</h1>
<c:if test="${empty authInfo}">
    <form action="/login" method="POST">
        <spring:message code="email"/> <input type="email" id="email" , name="email" , value=${email}> <br>
        <spring:message code="password"/> <input type="password" id="password" , name="password"> <br>
        <spring:message code="remember"/> <input type="checkbox" id="remember" , name="remember"> <br>
        <spring:message code="submit"/> <input type="submit">
    </form>
</c:if>
<c:if test="${!empty authInfo}">
    <form action="/logout">
        logout <input type="submit">
    </form>
    ${authInfo.name} - ${authInfo.email}
</c:if>

</body>
</html>