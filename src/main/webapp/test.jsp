<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: heewonseo
  Date: 2021/09/09
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    Connection conn = null;
    try {
        conn = DriverManager.getConnection("jdbc:oracle:thin:@db001_high?TNS_ADMIN=/Users/heewonseo/Documents/Oracle/Wallet", "BITUSER", "Wyfmel061300");
        out.print(conn.isClosed());
    } catch (SQLException e) {
        e.printStackTrace();
    }

%>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
