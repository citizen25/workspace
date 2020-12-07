<%@page import="board.model.QnADAO"%>
<%@page import="board.model.QnA"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	//넘겨 받은 파라미터를 이용하여 원 글 등록
	//또한 원 글의 team값을 곧바로 pk값으로 update
	
	//파라미터 받기
	request.setCharacterEncoding("utf-8");  //전송된 파라미터들이 깨지지 않도록
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	out.print(writer);
	out.print(title);
	out.print(content);
	
	//VO에 낱개로 된 파라미터 데이터 채워넣기
	QnA qna = new QnA();
	qna.setWriter(writer);
	qna.setTitle(title);
	qna.setContent(content);
	
	QnADAO dao = new QnADAO();
	int result = dao.insert(qna);  //1건 넣기
	
	/*
	if(result == 0){
		out.print(getMsgBack("등록 실패"));
	}else{
		out.print(getMsgURL("등록 성공", "/qna/list.jsp"));
	}
	*/
	
%>