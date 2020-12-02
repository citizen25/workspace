<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="db.DBManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%
  DBManager dbManager = new DBManager();
  Connection con = dbManager.getConnection();
  PreparedStatement pstmt = null;
  ResultSet rs = null;

  //select
  String sql = "select * from notice order by notice_id desc";
  pstmt = con.prepareStatement(sql);  //쿼리준비
  rs = pstmt.executeQuery();  //select문 실행

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

th, td {
  text-align: left;
  padding: 16px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
  $("button").on("click", function(){
	//자바스크립트에서 링크 구현은?
	location.href = "/board/regist_form.jsp";
  });
});  //onload
</script>
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
  <%while(rs.next()){%>
  <tr>
    <td><%=Integer.parseInt(rs.getString("notice_id"))%></td>
    <td>
	  <a href="/board/detail.jsp?notice_id=<%=rs.getInt("notice_id")%>">
	    <%=rs.getString("title")%>
	  </a>
	</td>
    <td><%=rs.getString("author")%></td>
    <td><%=rs.getString("regdate")%></td>
    <td><%=rs.getString("hit")%></td>
  </tr>
  <%}%>
  <tr>
    <td colspan="5">
	  <button>글 등록</button>
    </td>
  </tr>
    <tr>
    <td colspan="5" style="text-align:center">
	  <%@ include file="/inc/footer.jsp"%>
    </td>
  </tr>
</table>
<%
  dbManager.release(con, pstmt, rs);
%>
</body>
</html>
