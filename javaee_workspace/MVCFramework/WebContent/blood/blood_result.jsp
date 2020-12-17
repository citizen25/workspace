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
	당신이 선택한 혈액형에 대한 분석 결과 <p>
	<%=session.getAttribute("msg") %>
		<a href="/blood/blood_form.jsp">선택 폼으로 가기</a>
</body>
</html>