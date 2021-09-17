<%@page import="kr.or.bit.utils.Singleton_Helper"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="kr.or.bit.utils.ConnectionHelper" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//dao
	/*  
		1. 권한검사
		2. id 값 받기
		3. DB > delete from koreamember where id=?
		4. 이동처리 >> 삭제 완료시 >> 목록페이지로 이동		
	*/
	if (session.getAttribute("userid") == null || !session.getAttribute("userid").equals("admin")) {
		//강제로 다른 페이지 이동
		out.print("<script>location.href='Ex02_JDBC_Login.jsp'</script>");
	}

	String id = request.getParameter("id");

	//POOL
	DataSource ds = null;

	try {
		Context context = new InitialContext(); // 검색 기능
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle"); // java:comp/env/ + 이름\
	} catch (NamingException e) {
		System.out.println(e.getMessage());
	}

	//-------------POOL------------------//
	Connection conn = ds.getConnection();
	//-------------POOL------------------//

//	Class.forName("oracle.jdbc.OracleDriver");
//	Connection conn = null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	try {
//		conn = DriverManager.getConnection("jdbc:oracle:thin:@db001_high?TNS_ADMIN=/Users/heewonseo/Documents/Oracle/Wallet","ADMIN","Wyfmel0613**");
		String sql = "delete from koreamember where id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);

		int row = pstmt.executeUpdate();
		if (row > 0) {
			out.print("<script>");
			out.print("location.href='Ex03_Memberlist.jsp'");
			out.print("</script>");

		} else {
			//필요에 따라 추가
		}

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
//	if(pstmt != null){ try{pstmt.close();}catch(Exception e){} }
//	if(conn != null){ try{conn.close();}catch(Exception e){} }
		//오늘의 POINT
		ConnectionHelper.close(rs);
		ConnectionHelper.close(pstmt);

		//----반환----//
		ConnectionHelper.close(conn);
		//----반환----//
	}
%>


