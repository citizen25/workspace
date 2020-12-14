<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style></style>
<script></script>
</head>
<body>
	고양이가 발급한 세션 아이디는 
	<%
		/*
			jsp의 내장 객체
			-request, response, out, application, session 등등			
		*/
		String id = session.getId();  //고양이가 발급한 아이디
		out.print(id);
		
		//브라우저를 껏다 켯을 때 id는
		//45E82303D05F589E4216AA54FBEFF241 -> 65CA37C9BE6423277DE9089509B6ACCB
	%>
</body>
</html>