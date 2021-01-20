<%@page import="com.koreait.mvclegacy.model.domain.Hardware"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	List<Hardware> hardwareList = (List)request.getAttribute("hardwareList");
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

	<table>
		<tr>
			<th>name</th>
			<th>brand</th>
			<th>price</th>
		</tr>
		<%for(int i=0; i<hardwareList.size(); i++) { %>
		<%Hardware hardware = hardwareList.get(i); %>
		<tr>
			<td><%=hardware.getName() %></td>
			<td><%=hardware.getBrand() %></td>
			<td><%=hardware.getPrice() %></td>
		</tr>
		<%} %>
	</table>

</body>
</html>