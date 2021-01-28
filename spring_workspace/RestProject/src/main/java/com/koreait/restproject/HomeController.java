package com.koreait.restproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.restproject.model.domain.Member;

import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */
@Controller
@Slf4j
public class HomeController {
	
	@PostMapping("/main")
	@ResponseBody
	public String home(@RequestBody Member member) {
		
		log.info("m_id"+ member.getM_id());
		log.info("m_pass"+member.getM_pass());
		log.info("m_name"+member.getM_name());
		
		return "home";
	}
	
}
