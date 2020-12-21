package com.model2.notice.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.controller.Controller;
import com.model2.notice.model.NoticeDAO;

public class DeleteController implements Controller {

	NoticeDAO noticeDAO = new NoticeDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		int notice_id = Integer.parseInt(request.getParameter("notice_id"));
		noticeDAO.delete(notice_id);
	}

	public String getResultView() {
		return "/view/notice/delete";
	}

	public boolean isForward() {
		return false;
	}

}
