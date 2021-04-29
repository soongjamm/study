<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: soongjamm
  Date: 2021/01/28
  Time: 1:46 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="change.pwd.title"/></title>
</head>
<body>
    <form:form modelAttribute="command">
        <p>
            <label><spring:message code="currentPassword"/>:</br>
                <form:input path="currentPasssword"/>
                <form:errors path="currentPasssword" />
            </label>
        </p>
        <p>
            <label><spring:message code="newPassword"/>:</br>
                <form:input path="newPassword"/>
                <form:errors path="newPassword"/></label>
        </p>
        <input type="submit" value="<spring:message code="change.btn"/>">
    </form:form>
</body>
</html>
