<%@page import="board.model.Notice"%>
<%@ page import="board.model.NoticeDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ include file="/inc/lib.jsp" %>
<%
  NoticeDAO noticeDAO = new NoticeDAO();

  out.print("이 페이지에서 클라이언트가 전송한 파라미터들을, 데이터베이스에 넣을 에정");

  request.setCharacterEncoding("utf-8");

  String author = request.getParameter("author");  //작성자
  String title = request.getParameter("title");  //제목
  String content = request.getParameter("content");  //내용

  /*등록*/
  Notice notice = new Notice();
  notice.setAuthor(author);
  notice.setTitle(title);
  notice.setContent(content);
  
  int result = noticeDAO.regist(notice);  //VO, DTO
  
  if(result == 0){
	  out.print(getMsgBack("등록 실패"));
  }else{
	  out.print(getMsgURL("등록 성공", "/board/list.jsp"));
  }

%>