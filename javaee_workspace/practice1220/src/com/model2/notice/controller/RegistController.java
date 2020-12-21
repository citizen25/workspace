package com.model2.notice.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.controller.Controller;
import com.model2.notice.domain.Notice;
import com.model2.notice.model.NoticeDAO;

public class RegistController implements Controller {
	
	NoticeDAO noticeDAO = new NoticeDAO();

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		noticeDAO.insert(notice);
		
	}

	public String getResultView() {
		return "/view/notice/regist";
	}

	public boolean isForward() {
		return false;
	}

}
