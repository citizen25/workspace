<%@ page contentType="text/html;charset=utf-8"%>
<%
	//service 메서드 영역 고양이가 이 jsp로부터 생성한 servlet 클래스에서는
	//각종 예외가 throws 처리가 되어있기 때문에, jsp 스크립틀릿 영역에서는
	//예외처리를 강요하지 않은 것이다
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style></style>
<script></script>
</head>
<body>
	당신이 선택한 영화에 대한 판단 결과 <p>
	<%=session.getAttribute("msg") %>
	<a href="/movie/movie_form.jsp">선택 폼으로 가기</a>
</body>
</html>