package com.koreait.bootgradle.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.bootgradle.model.domain.Board;

@Controller
public class BoardController {
	
	@GetMapping("/rest/board/{board_id}")
	@ResponseBody //결과가  jsp 가 아닌 json   , 반환형이 String이 아닌VO형인경우, Converter가 지원되어야 한다..
	// 스프링설정 파일.xml  jackson jar다운받아.. MessageConverter...설정..(스프링부트는 이 등록이 설정되어있슴..)
	public Board getDetail(@PathVariable int board_id) {
		Board board = new Board();
		board.setBoard_id(board_id);
		board.setTitle("난 vo야");		
		return board;
	}

}




