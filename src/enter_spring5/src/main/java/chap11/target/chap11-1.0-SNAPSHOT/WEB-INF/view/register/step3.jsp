<%--
  Created by IntelliJ IDEA.
  User: soongjamm
  Date: 2021/01/26
  Time: 5:17 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
    <title>회원가입</title>
</head>

<body>
<p><strong>${formData.name}</strong>님 회원 가입을 완료했습니다.</p>
<p>
    <a href="<c:url value="/main" />">
    [첫 화면 이동]
    </a>
</p>

</body>
</html>
