<%@page import="admin.member.Admin"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//세션에서 데이터 꺼내기
	out.print("당신이 사용하고 있는 세션 객체는 " + session);
	Admin admin = (Admin)session.getAttribute("ad");
	
	
	
	//만일 admin VO가 null이면? 인증을 거치지 않고나, 세션이 만료된 상황이므로,
	//현재 페이지에 대한 접근 자체를 막아야 한다.
	if(admin != null) {
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/admin/inc/head.jsp" %>
<script></script>
</head>
<body>
	<div>
		<%=admin.getMid() %>님 로그인 중
		<a href="/admin/logout.jsp">로그아웃</a>
	</div>
	<%@ include file="/admin/inc/topnavi.jsp" %>
</body>
</html>
<%} else {%>

<script>
	alert("올바르지 않은 접근입니다.");
	history.back();
</script>

<%}%>