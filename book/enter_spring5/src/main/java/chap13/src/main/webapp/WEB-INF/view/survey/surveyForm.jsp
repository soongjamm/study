<%--
  Created by IntelliJ IDEA.
  User: soongjamm
  Date: 2021/01/26
  Time: 4:10 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>설문 조사</title>
</head>
<body>
<h2>설문 조사 입력</h2>
<form method="post">
    <c:forEach var="q" items="${questions}" varStatus="status">
        <p>
                ${status.index + 1}. ${q.title} </br>
            <c:if test="${q.choice}">
                <c:forEach var="option" items="${q.options}">
                    <label><input type="radio"
                                  name="responses[${status.index}]}" value="${option}">
                            ${option}</label>
                </c:forEach>
            </c:if>
            <c:if test="${!q.choice}">
                <input type="text" name="responses[${status.index}]">
            </c:if>
        </p>
    </c:forEach>
    <p>
        <label>응답자 위치:</label>
        <input type="text" name="res.location">
    </p>
    <p>
        <label>응답자 나이:</label>
        <input type="text" name="res.age">
    </p>
    <input type="submit" value="전송">
</form>
</body>
</html>
