package com.springmvc.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model2.board.model.BoardDAO;

public class ListController implements Controller {

	//BoardDAO boardDAO = new BoardDAO();  //new하지 말고 주입 받는다
	//DI란 외부에서 객체의 instance를 주입받는 것 (주입받으려면, setter or 생성자를 준비해야함)
	private BoardDAO boardDAO;
	
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//3단계: logic 객체에 일 시키기
		List boardList = boardDAO.selectAll();
		
		System.out.println("게시물 수 : " + boardList.size());
		
		//4단계: 결과 저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", boardList);
		mav.setViewName("board/list");
		
		return mav;
	}

}
