<%@page import="board.model.BoardDAO"%>
<%@page import="board.model.Board"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	BoardDAO boardDAO = new BoardDAO();
	ArrayList<Board> list = new ArrayList<Board>();
	list = (ArrayList<Board>)boardDAO.selectAll();
	
	int totalRecord = list.size();
	int pageSize = 10;
	int totalPage = (int)Math.ceil((float)totalRecord/pageSize);
	int blockSize = 10; 	
	int currentPage = 1;
	if(request.getParameter("currentPage") != null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int firstPage = currentPage - (currentPage-1)%blockSize;
	int lastPage = firstPage+(blockSize-1);
	int curPos = (currentPage-1)*pageSize;
	int num=totalRecord -  curPos;//
	
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
			<th>이미지</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
		
		<%for(int i=0; i<pageSize; i++){ %>
		<%if(num<1)break; %>
		<%Board board = list.get(curPos++); %>
		<tr>
			<td><%=num-- %></td>
			<td><img src="<%=board.getFilename()%>"></td>
			<td><%=board.getTitle() %></td>
			<td><%=board.getWriter() %></td>
			<td><%=board.getRegdate().substring(0,10) %></td>
			<td><%=board.getHit() %></td>
		</tr>
		<%} %>
		
		<tr>
			<td colspan="6" style="text-align:center">
				<%for(int i=firstPage; i<=lastPage; i++){ %>
				<%if(i>totalPage)break;%>
				<a href="list.jsp?currentPage=<%=firstPage-1%>">◀</a>
				<a href="list.jsp?currentPage=<%=i%>">[<%=i %>]</a>
				<a href="list.jsp?currentPage=<%=lastPage+1%>">▶</a>
				<%} %>
			</td>
		</tr>
		<tr>
			<td colspan="6">
				<button onClick="location.href='regist_form.jsp'">글 등록</button>
			</td>
		</tr>
	</table>

</body>
</html>
