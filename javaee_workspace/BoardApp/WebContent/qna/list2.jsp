<%@page import="board.model.QnA"%>
<%@page import="java.util.List"%>
<%@page import="board.model.QnADAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//DB 연동
	QnADAO dao = new QnADAO();
	List<QnA> list = dao.selectAll();

	int totalRecord = list.size();  //총 레코드 수, 실제 DB에 있는 데이터 수를 대입하면 된다
	int pageSize = 10;  //한 페이지당 보여질 레코드 수
	int totalPage = (int)Math.ceil((float)totalRecord/pageSize);
	int blockSize = 10;
	int currentPage = 1;  //현재 페이지
	
	//아래의 코드는 아무때나 하는게 아니다. 누군가가 파라미터를 넘겼을 때만. 즉, 페이지 넘버를 클릭한 경우를 전제로 parstInt한다.
	if(request.getParameter("currentPage") != null){  //페이지를 넘겼다면..
		currentPage = Integer.parseInt(request.getParameter("currentPage"));		
	}
	/*
	int firstPage = ((int)Math.ceil((float)currentPage/blockSize)-1)*blockSize+1;  //반복문의 시작값
	int lastPage = ((int)Math.ceil((float)currentPage/blockSize))*blockSize;  //반복문의 끝값
	*/
	int firstPage = currentPage - (currentPage-1)%blockSize;
	int lastPage = firstPage + blockSize - 1;
	int num = totalRecord - (currentPage-1)*pageSize;  //페이지 당 시작 번호 (힌트 : 위에 있는 모든 변수를 조합하면 쉽게 나온다)
	int curPos = (currentPage-1)*pageSize;  //페이지당 List에서의 시작 index
%>
<%="totalRecord : " + totalRecord + "<br>" %>
<%="pagaSize : " + pageSize + "<br>" %>
<%="totalPage : " + totalPage + "<br>" %>
<%="blockSize: " + blockSize + "<br>" %>
<%="currentPage: " + currentPage + "<br>" %>
<%="firstPage: " + firstPage + "<br>" %>
<%="lastPage: " + lastPage + "<br>" %>
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

th, td {
  text-align: left;
  padding: 16px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

.pageNum{
	font-size:20pt;
	color: red;
	font-weight: bold;
}

a{
	text-decoration: none;
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

  <%for(int i=1; i<=pageSize; i++){ %>
	  <%if(num>0){ %>
		<%//break문을 만나기 않았다는 것은 레코드가 잇다는 것이므로, break문 아래에서 데이터를 추출하자
			QnA qna = list.get(curPos++);  //1page:0~9, 2page:10~19%>
	  <tr>
	    <td><%=num--%></td>
	    <td>
	    	<%if(qna.getDepth()>0){  //답글인것만 %>
	    	<img src="/images/reply.png" style="margin-left:<%=20*qna.getDepth()%>">
	    	<%} %>
	    	<%=qna.getTitle()%>
	    </td>
	    <td><%=qna.getWriter()%></td>
	    <td><%=qna.getRegdate()%></td>
	    <td><%=qna.getHit()%></td>
	  </tr>
	  <%} %>
  <%} %>
  <tr>
    <td colspan="5">
	  <button>글 등록</button>
    </td>
  </tr>
  <tr>
   	<td colspan="5" style="text-align:center"> 
   		<%if(firstPage-1 > 0){ %>
			<a href="/qna/list2.jsp?currentPage=<%=firstPage-1%>">◀</a>
		<%}else{ %>
			<a href="javascript:alert('첫 페이지입니다')">◀</a>
		<%} %>
		<%for(int i=firstPage;i<=lastPage;i++){%>
			<%if(i>totalPage)break;  //페이지를 출력하는 i가 총 페이지수에 도달하면 반복문 빠져나오기 %>
			<a href="/qna/list2.jsp?currentPage=<%=i%>" <%if(currentPage==i){%>class="pageNum"<%}%>>
				[<%=i %>]
			</a>
		<%}%>
		<a href="/qna/list2.jsp?currentPage=<%=lastPage+1%>">▶</a>							
	</td>
  </tr>
  <tr>
    <td colspan="5" style="text-align:center">
	  <%@ include file="/inc/footer.jsp"%>
    </td>
  </tr>
</table>

</body>
</html>
