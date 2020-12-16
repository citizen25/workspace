//삭제 요청을 처리하는 컨트롤러 정의

package com.webapp1216.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webapp1216.board.model.NoticeDAO;

public class DeleteServlet extends HttpServlet {
	NoticeDAO noticeDAO = new NoticeDAO();
	
	//삭제 요청은 get? post? -> 둘 다 가능하지만, parameter가 notice_id밖에 없으므로, get으로 처리
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String notice_id = request.getParameter("notice_id");
		
		int result = noticeDAO.delete(Integer.parseInt(notice_id));
		
		//controller가 client가 보게될 메세지를 처리해야 한다? 아니다?
		//alert()는 디자인이므로, controller에서 처리하지 않는다
		HttpSession session = request.getSession();
		
		//result = 0;  //테스트 목적
		
		if(result == 0) {
			//실패 페이지를 보여준다. 결국 에러에 대한 안내를 보여주는 페이지를 별도로 두고, 그 페이지를 보여준다
			session.setAttribute("msg", "글 삭제에 실패했습니다. 관리자에게 문의하세요.");
			response.sendRedirect("/error/message.jsp");
		} else {
			response.sendRedirect("/board/list");
		}
	}
}
