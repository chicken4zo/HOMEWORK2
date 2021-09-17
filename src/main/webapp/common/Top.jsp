<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="id" value="${sessionScope.userid}"/>
<a href="Main.do">Main</a>&nbsp;&nbsp;&nbsp;||
<%--<a href="Ex02_JDBC_Login.jsp">Login</a>&nbsp;&nbsp;&nbsp;||--%>
<a href="Ex02_JDBC_JoinForm.jsp">Register</a>&nbsp;&nbsp;&nbsp;||
<a href="#">Intro</a>&nbsp;&nbsp; ||
<a href="#">Intro</a>&nbsp;&nbsp;&nbsp;
<span id="loginlogout"></span>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	// document.getElementById("loginlogout").addEventListener('click',function (e) {
	//     e.preventDefault();
	// 	sendData();
	// });

	$(document).ready(function () {
		$.ajax(
				{
					url: "LoginLogout",
					type: "GET",
					dataType: "html",
					data: "${id}",
					success: function (responseData) {
						document.getElementById("loginlogout").innerHTML = responseData;
					},
					error: function(xhr){
						console.log(xhr.status);
					}
				}
		);
	});




	// let httpReq=null;
	// sendData();
	//
	// function getInstance(){
	// 	if(window.XMLHttpRequest){
	// 		httpReq = new XMLHttpRequest(); //현재 브라우져 XMLHttpRequest 내장
	// 		console.log("get instance");
	// 	}else if(window.ActiveXObject){ //IE6 이하   //지금 필요없어요
	// 		httpReq = new ActiveXObject("Msxml2.XMLHTTP");
	// 	}else{
	// 		throw new Error("AJAX 지원하지 않습니다");
	// 	}
	// 	return httpReq;
	// }
	//
	// function handlerStateChange(){
	// 	console.log("")
	// 	console.log(getInstance());
	// 	if(httpReq.readyState === 4){ //1. 서버에서 응답이 왔다면
	// 		console.log(httpReq.status);
	// 		if(httpReq.status >= 200 && httpReq.status < 300){
	// 			//가공
	// 			console.log("sendData");
	// 			document.getElementById("loginlogout").innerHTML = httpReq.responseText;
	// 		}else{
	// 			alert("status Text : " + httpReq.status);
	// 		}
	// 	}
	// }
	//
	// function sendData(){
	// 	httpReq = getInstance();
	// 	httpReq.onreadystatechange = handlerStateChange;  // x >> ()
	// 	httpReq.open("GET","LoginLogout"); //form 태그 구성
	//     httpReq.send();
	// }


</script>
