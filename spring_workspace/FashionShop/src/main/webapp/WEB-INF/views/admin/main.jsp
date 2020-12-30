<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="./inc/header.jsp" %>
</head>
<body>
	<!-- 메인 네비게이션 영역 : jsp는 이제 보인된 폴더에 있으므로
	웹사이트 기준의 절대경로를 사용할 수 없다. 따라서 상대경로(../)을 사용한다 -->
	<%@ include file="./inc/main_navi.jsp" %>


	<h3>Dropdown Menu inside a Navigation Bar</h3>
	<p>Hover over the "Dropdown" link to see the dropdown menu.</p>

</body>
</html>