//댓글 등록 요청을 처리하는 controller

package com.model2.comment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.comment.model.CommentDAO;
import com.model2.controller.Controller;
import com.model2.domain.Comment;

public class RegistController implements Controller {

	CommentDAO commentDAO = new CommentDAO();
	
	//댓글의 등록은 비동기 요청으로 들어오므로, 응답 정보는 페이지를 보여주는게 아니라 데이터를 전송해야함
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터값이 넘어오는지부터 확인해보자
		String board_id = request.getParameter("board_id");
		String msg = request.getParameter("msg");
		String author = request.getParameter("author");
		
		//System.out.println("msg : " + msg);
		//System.out.println("author : " + author);
		
		//vo에 담기
		Comment comment = new Comment();
		comment.setBoard_id(Integer.parseInt(board_id));
		comment.setMsg(msg);
		comment.setAuthor(author);
		
		//등록 메서드 호출
		int result = commentDAO.insert(comment);
		
		//4단계: DML 수행 결과를 저장한다
		request.setAttribute("result", result);  //boxing이 일어나 object만 올 수 있는 곳에 int형도 가능해졌다
		
	}

	public String getResultView() {
		return "/view/comment/regist";  //client에게 보여줄 등록 결과의 정보를 보여주는 jsp key값
	}

	public boolean isForward() {
		return true;
	}

}
