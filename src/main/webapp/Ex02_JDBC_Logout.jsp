<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--controller--%>
<%
	session.invalidate();
	response.sendRedirect("Ex02_JDBC_Login.jsp");
%>    
