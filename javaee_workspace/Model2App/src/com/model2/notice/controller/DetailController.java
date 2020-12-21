package com.model2.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.controller.Controller;
import com.model2.domain.Notice;
import com.model2.notice.model.NoticeDAO;

public class DetailController implements Controller {

	 NoticeDAO noticeDAO = new NoticeDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계 : 알맞은 logic 객체에 일 시킨다
		Notice notice = noticeDAO.select(Integer.parseInt(request.getParameter("notice_id")));
		
		//4단계 : 저장할 결과가 있다면 저장하기 (저장을 했다면, 당연히 이 request객체가 jsp까지 살아서 가야하므로 forwarding해야한다)
		request.setAttribute("notice", notice);
	}

	public String getResultView() {
		return "/view/notice/detail";
	}

	public boolean isForward() {
		return true;
	}

}
