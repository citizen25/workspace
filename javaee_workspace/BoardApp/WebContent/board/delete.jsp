<%@page import="board.model.NoticeDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="board.db.DBManager"%>
<%@ include file="/inc/lib.jsp"%>
<%
	request.setCharacterEncoding("utf-8");
	
	String notice_id = request.getParameter("notice_id");

	NoticeDAO noticeDAO = new NoticeDAO();

	int result = noticeDAO.delete(Integer.parseInt(notice_id));
	
	if(result == 0){
		out.print(getMsgBack("삭제 실패"));
	}else{
		out.print(getMsgURL("삭제 성공", "/board/list.jsp"));
	}

%>