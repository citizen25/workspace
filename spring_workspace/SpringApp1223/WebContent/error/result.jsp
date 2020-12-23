<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style></style>
<script>
	alert("<%=request.getAttribute("msg")%>");
	history.back();
</script>

</head>
<body bgcolor="yellow">

</body>
</html>