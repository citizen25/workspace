//이 클래스가 하위 Controller로써 역할을 수행해야하므로
//반드시 Controller interface를 구현해야한다

package com.model2.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.controller.Controller;

public class TestController implements Controller {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계 : 일시키기
		String msg = "테스트입니다";
		
		//4단계 : 결과 저장
		HttpSession session = request.getSession();
		session.setAttribute("result", msg);
	}

	public String getResultView() {
		
		return "/view/test/result";  //외부 데이터 환경(로직과 상관 없음)
			//파일명이 하드코딩되어있다
	}

}
