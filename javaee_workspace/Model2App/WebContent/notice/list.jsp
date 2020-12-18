<%@page import="com.model2.notice.domain.Notice"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="common.board.Pager"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//jsp에서는 이미 내장객체로 지원되기 때문에, session으로 사용하면 됨
	//List list = (List)session.getAttribute("noticeList");
	List list = (List)request.getAttribute("noticeList");

	Pager pager = new Pager();
	pager.init(request, list);  //페이징 처리에 대한 계산
%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

a{text-decoration:none;}

button {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

button:hover {
	background-color: #45a049;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}
</style>
</head>
<body>



<table>
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
		<%
			int num = pager.getNum(); 
			int curPos = pager.getCurPos();
		%>
		<%for(int i=0; i<pager.getPageSize(); i++){ %>
		<%if(num < 1) break; %>
		<%Notice notice = (Notice)list.get(curPos++); %>
		<tr>
			<td><%=num-- %></td>
			<td><a href="/notice/detail.do?notice_id=<%=notice.getNotice_id()%>"><%=notice.getTitle() %></a></td>
			<td><%=notice.getWriter() %></td>
			<td><%=notice.getRegdate().substring(0,10) %></td>
			<td><%=notice.getHit() %></td>
		</tr>
		<%} %>
		
		<tr>
			<td colspan="6">
				<button onClick="location.href='regist_form.jsp'">글 등록</button>
			</td>
		</tr>
		
		
		<tr>
			<td colspan="6" style="text-align:center">
				<a href="list.do?currentPage=<%=pager.getFirstPage()-1%>">◀</a>
				<%for(int i=pager.getFirstPage(); i<=pager.getLastPage(); i++){ %>
				<%if(i > pager.getTotalPage()) break;%>
				<a href="list.do?currentPage=<%=i%>">[<%=i %>]</a>
				<%} %>
				<a href="list.do?currentPage=<%=pager.getLastPage()+1%>">▶</a>
			</td>
		</tr>
	</table>
	
</body>
</html>
