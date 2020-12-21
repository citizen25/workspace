//comment 게시판의 list 요청을 처리하는 controller
//이 controller는 servlet은 아니다. 단지 servlet으로부터 
//요청, 응답 객체를 전달받아 처리하는 역할이다

package com.model2.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.board.model.BoardDAO;
import com.model2.controller.Controller;

public class ListController implements Controller {

	BoardDAO boardDAO = new BoardDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("코멘트 게시판 목록 요청을 처리합니다");
		//3단계 : 알맞은 로직 객체에 일 시키기
		List list = boardDAO.selectAll();
		
		//4단계 : 저장할 것이 있기 때문에 request에 저장
		request.setAttribute("boardList", list);
	}

	public String getResultView() {
		return "/view/board/list";
	}

	public boolean isForward() {
		return true;  //저장한 것이 있으므로, 요청이 유지되어야하고, 따라서 forwarding이 필요
	}

}
