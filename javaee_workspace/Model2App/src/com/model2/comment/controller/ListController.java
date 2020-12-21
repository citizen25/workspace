//댓글 목록 요청을 처리하는 controller

package com.model2.comment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.comment.model.CommentDAO;
import com.model2.controller.Controller;

public class ListController implements Controller {

	CommentDAO commentDAO = new CommentDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계: comment list 가져오기
		String board_id = request.getParameter("board_id");
		List commentList = commentDAO.selectAll(Integer.parseInt(board_id));
		
		//4단계: 저장할 것이 있다면 결과 저장(request에)
		request.setAttribute("commentList", commentList);
		
	}

	public String getResultView() {
		return "/view/comment/list";
	}

	public boolean isForward() {
		return true;
	}

}
