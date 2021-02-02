<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: soongjamm
  Date: 2021/02/01
  Time: 1:06 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>인덱스</title>
</head>
<body>
<h1>인덱스 입니다.</h1>
<c:if test="${! empty authInfo}">
    <h3>현재 로그인 중</h3>
    이름 : ${authInfo.name}</br>
    아이디 : ${authInfo.email}
    <a href="/logout">[로그아웃]</a>

</c:if>
<c:if test="${empty authInfo}">
<p>
    <h3>회원가입이나 로그인하세요.</h3>
    <a href="/user/register/terms">[회원 가입]</a>
    <a href="/login">[로그인]</a>
</p>
</c:if>
</body>
</html>
