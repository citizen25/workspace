package com.springmvc.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//이 class는 요청을 실제적으로 처리하는 controller임을 명시 - annotation -> 그러나 지금은 쓰지 않는다
//추구하는 목표 : POJO(Plain Old Java Object) 기반으로 가려는 경향이 강함

public class TestController implements Controller{
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//3단계: 알맞는 logic 객체에 일 시키기
		String msg = "안녕";
		
		//4단계: 저장할 것이 있다면 request객체에 저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		
		//형님 controller가 어떤 page를 보여줘야할지에 대한 정보는 여전히 
		mav.setViewName("test/result");
		
		return mav;
	}


}
