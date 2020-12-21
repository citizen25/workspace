package com.model2.notice.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.controller.Controller;
import com.model2.notice.domain.Notice;
import com.model2.notice.model.NoticeDAO;

public class DetailController implements Controller {

	NoticeDAO noticeDAO = new NoticeDAO();

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Notice notice = noticeDAO.select(Integer.parseInt(request.getParameter("notice_id")));
		request.setAttribute("notice", notice);
	}

	public String getResultView() {
		return "/view/notice/detail";
	}

	public boolean isForward() {
		return true;
	}

}
