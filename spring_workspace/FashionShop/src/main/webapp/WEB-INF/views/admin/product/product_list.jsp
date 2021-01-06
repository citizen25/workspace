<%@page import="com.koreait.fashionshop.model.domain.Product"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List<Product> productList = (List)request.getAttribute("productList");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../inc/header.jsp" %>
<style>

</style>
<script>

	$(function(){
		$("button").click(function(){
			location.href = "/admin/product/registform";  //글쓰기 폼 요청
		});
	});

</script>
</head>
<body>
	<!-- 메인 네비게이션 영역 -->
	<%@ include file="../inc/main_navi.jsp" %>

	<h3>상품목록</h3>
	<table>
		<tr>
			<th>No</th>
			<th>이미지</th>
			<th>카테고리명</th>
			<th>상품명</th>
			<th>가격</th>
			<th>브랜드</th>
		</tr>
		<%for(int i=0; i<productList.size(); i++){ %>
		<%Product product = productList.get(i); %>
		<tr>
			<td>No</td>
			<td><img src="/resources/data/basic/<%=product.getProduct_id() %>.<%=product.getFilename() %>" width="50px"></td>
			<td><%=product.getSubCategory().getName() %></td>
			<td><%=product.getProduct_name() %></td>
			<td><%=product.getPrice() %></td>
			<td><%=product.getBrand() %></td>
		</tr>
		<%} %>
		<tr>
			<td colspan="6">
				<button>상품 등록</button>
			</td>
		</tr>

	</table>

</body>
</html>