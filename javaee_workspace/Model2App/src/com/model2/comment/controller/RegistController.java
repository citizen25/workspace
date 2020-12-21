//��� ��� ��û�� ó���ϴ� controller

package com.model2.comment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.comment.model.CommentDAO;
import com.model2.controller.Controller;
import com.model2.domain.Comment;

public class RegistController implements Controller {

	CommentDAO commentDAO = new CommentDAO();
	
	//����� ����� �񵿱� ��û���� �����Ƿ�, ���� ������ �������� �����ִ°� �ƴ϶� �����͸� �����ؾ���
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�Ķ���Ͱ��� �Ѿ���������� Ȯ���غ���
		String board_id = request.getParameter("board_id");
		String msg = request.getParameter("msg");
		String author = request.getParameter("author");
		
		//System.out.println("msg : " + msg);
		//System.out.println("author : " + author);
		
		//vo�� ���
		Comment comment = new Comment();
		comment.setBoard_id(Integer.parseInt(board_id));
		comment.setMsg(msg);
		comment.setAuthor(author);
		
		//��� �޼��� ȣ��
		int result = commentDAO.insert(comment);
		
		//4�ܰ�: DML ���� ����� �����Ѵ�
		request.setAttribute("result", result);  //boxing�� �Ͼ object�� �� �� �ִ� ���� int���� ����������
		
	}

	public String getResultView() {
		return "/view/comment/regist";  //client���� ������ ��� ����� ������ �����ִ� jsp key��
	}

	public boolean isForward() {
		return true;
	}

}
