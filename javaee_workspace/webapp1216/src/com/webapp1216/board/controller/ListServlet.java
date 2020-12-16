//javaEE ���� ���� �� mvs������ ������ ���� ����� ������ model2 ����̶� ���´´�
//Ư�� jsp�� �����ο� ���ǰ� �����Ƿ�, ������ ��û�� �ް� ������ ������ ������
//��Ʈ�ѷ��μ� ������ �����ϰ� �ȴ�

package com.webapp1216.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webapp1216.board.model.NoticeDAO;

//client�� ��� ��û�� ó���ϴ� servlet ����
public class ListServlet extends HttpServlet{
	NoticeDAO noticeDAO = new NoticeDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List list = noticeDAO.selectAll();
	
		//�����ϰ� ���� list�� �����������. ���� ������ ���?? ����
		//session? client�� browser process�� ���� �ʰų�, ���� �ð� ���� �������� ��,
		//�������� �޸𸮿� ����� ������ ����� �� �ִ� ���
		//(���ο� ������ ���, session��ü�� ���� �����ǰ�, session id�� ���Ӱ� �߱޵ȴ�)
		//jsp������ session ���� ��ü�� �ڷ����� HttpSession�̴�
		HttpSession session = request.getSession();  //�� ��û�� ������ ������ ��´�
		session.setAttribute("noticeList", list);  //session ��ü�� ����
		
		//��� ������ ����
		response.sendRedirect("/board/list.jsp");
	}
	
}
