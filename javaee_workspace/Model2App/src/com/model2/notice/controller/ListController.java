//수 많은 요청 중에서 오직 공지 게시판의 목록 요청을 처리하는 하위 controller

package com.model2.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.controller.Controller;
import com.model2.notice.model.NoticeDAO;

public class ListController implements Controller {

	NoticeDAO noticeDAO = new NoticeDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계: 알맞은 logic 객체에게 일 시킨다
		List list = noticeDAO.selectAll();
		
		//4단계: client가 봐야할 결과가 있다면, 결과 저장
		//그래야 DispatcherServlet controller가 사용할 수 있다
		//HttpSession session = request.getSession();
		//session.setAttribute("noticeList", list);
		request.setAttribute("noticeList", list);
	}

	public String getResultView() {
		return "/view/notice/list";
	}

	public boolean isForward() {
		return true;
	}
	
}
