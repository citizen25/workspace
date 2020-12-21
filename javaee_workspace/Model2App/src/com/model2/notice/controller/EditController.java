package com.model2.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.controller.Controller;
import com.model2.domain.Notice;
import com.model2.notice.model.NoticeDAO;

public class EditController implements Controller {

	NoticeDAO noticeDAO = new NoticeDAO();
	Notice notice;
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		notice = new Notice();
		
		String notice_id = request.getParameter("notice_id");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		notice.setNotice_id(Integer.parseInt(notice_id));
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		noticeDAO.update(notice);
		
		request.setAttribute("notice", notice);
	}

	public String getResultView() {
		return "/view/notice/edit?notice_id=" + Integer.toString(notice.getNotice_id());
	}

	public boolean isForward() {
		return false;
	}

	
	
}
