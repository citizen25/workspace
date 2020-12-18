//�� ���� ��� Ŭ���̾�Ʈ�� ��û�� �ް�, ������ �����ϴ� ��Ʈ�ѷ� ����

package com.model2.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DispatcherServlet extends HttpServlet {

	FileReader fis;  //controller mapping ���� ������ �б� ���� stream
	JSONObject controllerMap;  //controller�� �������� ����ִ� map
	JSONObject viewMap;
	
	public void init(ServletConfig config) throws ServletException {
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");
		String realPath = config.getServletContext().getRealPath(contextConfigLocation);
		System.out.println(realPath);
		
		try {
			fis = new FileReader(realPath);
			JSONParser jsonParser = new JSONParser();
			
			//�Ľ�
			JSONObject json = (JSONObject)jsonParser.parse(fis);

			//�����Ϳ� ����
			controllerMap = (JSONObject)json.get("controller");
			viewMap = (JSONObject)json.get("view");
			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	//1�ܰ� : doXXX�� �޼��带 �����Ͽ� ��û�� ����
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  //������ ��� request�� ���� encoding
		
		//----2�ܰ� : ��û�� �м��Ѵ�----
		String uri = request.getRequestURI();  //client�� ��û �� ����� uri��ü�� ��û�� ���а����� ���� �� �ִ�
		
		//if���� ����� "����ȭ"�� �����͸� ��������(xml, json, properties) -> ������ json����
		String controllerName = (String)controllerMap.get(uri);
		System.out.println("���� ���� ��û�� ó���� controller class�� " + controllerName);
		
		//���� ���� Controller�� �̸��� �˾�����, instance�� �����, execute(), getResultView()�� ȣ��
		Class controllerClass = null;
		Controller controller = null;
		try {
			controllerClass = Class.forName(controllerName);  //String ��, ���ڿ��� ������ Ŭ������ ���� ���� Ŭ���� ��ȯ
			controller = (Controller)controllerClass.newInstance();  //���� Controller�� instance ����
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		//3�ܰ� : �Ͻ�Ű��
		controller.execute(request, response);
		//���� controller�κ��� �Ѱܹ��� view�� ���� ������ �̿��Ͽ� client�� �˸´� view�� ��������
		String resultKey = controller.getResultView();
		System.out.println("���� controller���� �Ѱܹ��� Ű ���� " + resultKey);
		
		//���� controller�κ��� �Ѱܹ��� key���� �̿��Ͽ� ���� �������� �˻��ϰ�,
		//�� ����� �̿��Ͽ� client�� ���Ե� �������� ��������
		String viewPage = (String)viewMap.get(resultKey);  //jsp ��ȯ
		
		//���� ��, sendRedirect�� ó���ؾ��� ��찡 �ְ�, (�� �ۼ� ��, list�� ���� �ٸ� �������� �������� �õ��ϰ� �� ��)
		//���δ� forwarding ó���ؾ��� ��찡 �ִ� (�����͸� �����ϰ� ���� ��)
		if(controller.isForward()) {  //������ ������ ��
			RequestDispatcher dis = request.getRequestDispatcher(viewPage);
			dis.forward(request, response);  //���� ����, ���� ���� �� �ٸ� �ڿ����� ��û�� ����			
		} else {  //client�� �Ͽ��� ���Ӱ� ������ �õ��ϰ��� ���
			response.sendRedirect(viewPage);			
		}
		

	}

	public void destroy() {
		if(fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
