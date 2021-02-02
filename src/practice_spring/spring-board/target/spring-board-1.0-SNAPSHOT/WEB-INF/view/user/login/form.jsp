<%--
  Created by IntelliJ IDEA.
  User: soongjamm
  Date: 2021/02/02
  Time: 4:33 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="/login" method="post" >
    <p>
        <label>${error}</label>
        <label>로그인 이메일</label>
        <input type="email" name="email" id="email">
    </p>
    <p>
        <label>비밀번호</label>
        <input type="password" name="password" id="password">
    </p>
    <input type="submit">
</form>
</body>
</html>
