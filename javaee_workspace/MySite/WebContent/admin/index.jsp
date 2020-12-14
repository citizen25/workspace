<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/admin/inc/head.jsp" %>
<script></script>
</head>
<body>
	<div><%=request.getParameter("admin_id") %></div>님 로그인 중
	<%@ include file="/admin/inc/topnavi.jsp" %>
</body>
</html>