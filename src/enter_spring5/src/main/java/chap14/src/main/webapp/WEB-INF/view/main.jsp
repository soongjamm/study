<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: soongjamm
  Date: 2021/01/26
  Time: 5:59 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>메인</title>
</head>
<body>
<c:if test="${empty authInfo}">
    <p>환영합니다.</p>
    <p>
        <a href="<c:url value="/register/step1" />">[회원가입]</a>
        <a href="<c:url value="/login" />">[로그인]</a>
    </p>
</c:if>
<c:if test="${!empty authInfo}">
    <p>${authInfo.name}님, 환영합니다.</p>
    <p>
        <a href="<c:url value="/edit/changePassword" />">[비밀번호 변경]</a>
        <a href="<c:url value="/logout" />">[로그아웃]</a>
    </p>
</c:if>

</body>
</html>
