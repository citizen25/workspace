package com.springmvc.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//�� class�� ��û�� ���������� ó���ϴ� controller���� ��� - annotation -> �׷��� ������ ���� �ʴ´�
//�߱��ϴ� ��ǥ : POJO(Plain Old Java Object) ������� ������ ������ ����

public class TestController implements Controller{
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//3�ܰ�: �˸´� logic ��ü�� �� ��Ű��
		String msg = "�ȳ�";
		
		//4�ܰ�: ������ ���� �ִٸ� request��ü�� ����
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		
		//���� controller�� � page�� ������������� ���� ������ ������ 
		mav.setViewName("test/result");
		
		return mav;
	}


}
