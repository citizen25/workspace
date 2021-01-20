package com.koreait.restproject.rest.controller.member;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.restproject.model.domain.Member;

import lombok.extern.slf4j.Slf4j;

@RestController    // 일반 Controller에 ResponseBody가 탑재된 Controller
@Slf4j
public class MemberController {

	@GetMapping("/member")
	public String getList() {
		log.debug("리스트 요청했어?");
		return "ha ha ah";
	}
	
	@PostMapping("/member")
	public String regist(@RequestBody Member member) {
		log.debug("등록을 원해?");
		log.debug("m_id : " + member.getM_id());
		log.debug("m_pass : " + member.getM_pass());
		log.debug("m_name : " + member.getM_name());
		return "regist success";
	}
}
