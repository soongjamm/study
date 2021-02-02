<%--
  Created by IntelliJ IDEA.
  User: soongjamm
  Date: 2021/02/01
  Time: 3:58 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/user/register/submit" method="post" >
    <p>
        <label>로그인 이메일</label>
        <input type="email" name="email" id="email">
        <label>${error}</label>
    </p>
    <p>
        <label>비밀번호</label>
        <input type="password" name="password" id="password">
    </p>
    <p>
        <label>비밀번호 확인</label>
        <input type="password" name="confirmedPassword" id="confirmedPassword">
    </p>
    <p>
        <label>이름</label>
        <input type="text" name="name" id="name">
    </p>
    <input type="submit">
</form>
</body>
</html>
