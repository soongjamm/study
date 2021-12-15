<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.mysql.jdbc.Driver" %>

<%
    Class.forName("com.mysql.jdbc.Driver");

    String idValue = request.getParameter("id");


    Connection conn = null;
    PreparedStatement pstmtItem = null;
    PreparedStatement pstmtDetail = null;
    ResultSet rs = null;

    String jdbcDriver = "jdbc:mysql://localhost:3306/chap14?useUnicode=true&characterEncoding=utf8";
    String dbUser = "jspexam";
    String dbPassword = "Jsppw#1234";

    Throwable occurException = null;

    try {
        int id = Integer.parseInt(idValue);

        conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPassword);
        conn.setAutoCommit(false);

        pstmtItem = conn.prepareStatement("insert into ITEM values (?,?)");
        pstmtItem.setInt(1, id);
        pstmtItem.setString(2, "상품 이름" + id);
        pstmtItem.executeUpdate();

        if (request.getParameter("error") != null) {
            throw new Exception("의도적 익셉션");
        }

        pstmtDetail = conn.prepareStatement("insert into ITEM_DETAIL values(?,?)");
        pstmtDetail.setInt(1, id);
        pstmtDetail.setString(2, "상세 설명 " + id);
        pstmtDetail.executeUpdate();

        conn.commit();
    } catch(Throwable e) {
        if (conn!=null) {
           try {
               conn.rollback();
           } catch (SQLException ex){}
        }
        occurException = e;
    }  finally {
        if (rs != null) try {
            rs.close();
        } catch (SQLException ex) {
        }
        if (pstmtDetail != null) try {
            pstmtDetail.close();
        } catch (SQLException ex) {
        }
        if (pstmtItem != null) try {
            pstmtItem.close();
        } catch (SQLException ex) {
        }

        if (conn != null) try {
            conn.close();
        } catch (SQLException ex) {
        }
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>item 값 입력</title>
</head>
<body>
<% if (occurException != null) { %>
에러 발생 : <%=occurException.getMessage()%>
<% } else { %>
ok. <%}%>
</body>
</html>