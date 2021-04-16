<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.mysql.jdbc.Driver" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>이름 변경 폼</title>
</head>
<body>

<form action="/chap14/update/update.jsp" method="post">
<table width="100%" border="1">
    <tr>
        <td>아이디</td>
        <td><input type="text" name="memberID" size="10"></td>
        <td>이름</td>
        <td><input type="text" name="name" size="10"></td>
    </tr>
    <tr>
        <td colspan="4"><input type="submit" value="변경"></td>
    </tr>
</table>
</form>
</body>
</html>