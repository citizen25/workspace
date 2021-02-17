package com.koreait.bootgradle.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminBoardController {
	
	//리스트 요청 처리 
	@GetMapping("/admin/board")
	public String getList() {
		return "admin/board/list";
	}
	
}
