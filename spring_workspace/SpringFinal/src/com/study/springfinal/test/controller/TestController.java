package com.study.springfinal.test.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//시스템에 의존적이지 않은 POJO가 목표
//POJO : Plain Old Java Object 추구

@Controller
public class TestController {

	//어떤 uri에 반응할지. 즉, 어떤 request를 처리할지 결정
	@RequestMapping(value="/hi", method=RequestMethod.GET)
	public ModelAndView test() {
		ModelAndView mav = new ModelAndView("test/result");  //setViewName과 동일
		
		return mav;
	}
	
}
