//���� javaEE����� ������������ ����� �� �ִ� Ŭ������ ������ �����̶� �Ѵ�

package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//�׳��� ������ ��û�� ���� �� ����. �׳� �Ϲ� Ŭ�����̱� ������..
//�׷��� HttpServlet�� ��ӹ޴� �������� ���������� ����� �� �ִ� Ŭ������ ������ �ȴ�
public class MyServlet extends HttpServlet{
	
	//Ŭ���̾�Ʈ�� get������� ��û�� ��, �� ��û�� ó���ϴ� �޼����̴�
	//HttpServlet�� �޼���κ��� ��ӹ޾�����, �� ������ �츮�� override�ؼ� ��û�� ó������
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//client�� ���ڿ� ����
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//��� : ������ �̿��Ͽ� html�� �����Ϸ���, ���ڿ��� ó���ؾ� �Ѵ�
		out.print("<table>");
		out.print("<tr>");
		out.print("<td>No</td>");
		out.print("<td>�̹���</td>");
		out.print("<td>����</td>");
		out.print("<td>�ۼ���</td>");
		out.print("<td>�����</td>");
		out.print("<td>��ȸ��</td>");
		out.print("</tr>");
		out.print("<table>");
	}
}
