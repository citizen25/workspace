<%@page import="board.model.ImageBoardDAO"%>
<%@page import="java.io.File"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	//넘겨받은 board_id를 이용하여 삭제
	String board_id = request.getParameter("board_id");
	String filename = request.getParameter("filename");
	
	out.print("지우게  게시물 board_id = " + board_id);
	
	//삭제 업무(db삭제 + 파일 삭제)
	//파일 삭제를 먼저하는 이유 : 데이터의 무결성을 위해
	
	//파일 삭제(파일을 다룰 수 있는 클래스 : java.io.File)
	File file = new File("C:/workspace/javaee_workspace/BoardApp/WebContent/data/" + filename);
	if(file.delete()){
		//DB 삭제
		ImageBoardDAO dao = new ImageBoardDAO();
		dao.delete(Integer.parseInt(board_id));
		out.print(getMsgURL("삭제처리되었습니다.", "/imageBoard/list.jsp"));
	}else{
		out.print(getMsgBack("삭제되지 않았습니다."));
	}
	
%>