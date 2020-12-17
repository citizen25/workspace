//MVC �������� �����ϴٺ���, �þ�� ��Ʈ�ѷ��� ���� ������ mapping������ �����ؾ��Ѵ�
//�̶� �ʹ� ���� mapping�� �����ϱⰡ ��ٷӴ�. --> ������������ ��������
//���ǰ� �����ϰ�, application������ ���� ���� ó�� ��, client�� ��û�� ��ٷ� �ش� controller����
//ó���ϰ� ���� �ʰ�, �߰��� main controller�� �ΰ�, ��� ��û�� �� main controller���� ó���Ͽ�
//������ ���� controller���� �����Ű�� ����� �̿��Ѵ�
//�� controller�� web applictaion�� ��� ��û�� 1�������� �޴´�. (�� ������ �й�)
//��û�� get���� post���� �𸥴�..

package com.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DispatcherServlet extends HttpServlet {
	
	FileInputStream fis;
	Properties props;

	//init�� servlet�� �����ֱ⿡��, ���� �����ڿ� ���� tomcat�� instance�� �����ϸ�,
	//�̿� ���ÿ� �ʱ�ȭ �޼���μ� ȣ��ȴ�
	public void init(ServletConfig config) throws ServletException {

		//getRealPath�� jsp�� ���尴ü �� application�� ���� ������ ����
		//application ���� ��ü���� ������
		ServletContext context = config.getServletContext();
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");
		String savePath = context.getRealPath(contextConfigLocation);
		
		System.out.println(savePath);
		
		try {
			fis = new FileInputStream(savePath);
			props = new Properties();
			props.load(fis);  //stream�� properties ����
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doRequest(request, response);
	}
	
	//get or post �������, ��� ��û�� �� �޼��忡�� ó������
	//���� ��Ʈ�ѷ��� ���� ��Ʈ�ѷ� ���� ���� �帧
	//1. ���� ��Ʈ�ѷ��� ��û�� �޴´�
	//2. ��û�� �м��Ͽ�, �˸��� ���� ��Ʈ�ѷ��� ��û ����
	//3. ���� ��Ʈ�ѷ��� �˸��� ���� ��ü���� �� ��Ų��
	//4. ����� ���� ���� �� ����� ����
	//5. �˸´� ����� �����ش�
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  //�Ķ���Ϳ� ���� ���ڵ�
			//���ڵ��� �� ���� �������� �ֱ� ���ؼ�, �������� �ѹ��� �ع�����

		//1�ܰ� : ���� ��Ʈ�ѷ��� ��û�� �޴´�
		System.out.println("���� ��û�� �޾Ҿ�� �̤�");
		
		//Ŭ���̾�Ʈ�� ��ȭ�� ���ϸ�? -> ��ȭ ��� ��Ʈ�ѷ����� ����
		//Ŭ���̾�Ʈ�� �������� ���ϸ�? -> ������ ��� ��Ʈ�ѷ����� ����
		
		//2-1�ܰ� : ��û�� �м��Ѵ�
		//�۾���?, ����?, ������?, ��ȭ?, .... ����� �˾Ƹ��߱�
		//Ŭ���̾�Ʈ�� ���ϴ� ���� ���� �˾ƾ��Ѵ�
		//�ش��� Ŭ���̾�Ʈ ��û ��ü�� �ִ�. ��, ��û�� ���� uri�� �� ��û ���а��̴�
		//URL: http://localhost:9999/movie.do
		//URI: movie.do
		String uri = request.getRequestURI();
		System.out.println("���� ���� ��û�� " + uri);
		
		Controller controller = null;
		
		String className = null;
		
		//if���� ����� �� �ִ� �ܺ������� ����ؼ� className�� �ذ�����
		//properties ��ü�� �̿��Ͽ� key������ �޸𸮿� �÷��� ��Ʈ�ѷ��� �̸��� value�� ��ȯ����
		className = props.getProperty(uri);
		
		try {
			Class controllerClass = Class.forName(className);  //Ŭ���� �ε�
			//�ν��Ͻ� ����
			controller = (Controller)controllerClass.newInstance();
			
			//2-2�ܰ� : �˸��� ���� ��Ʈ�ѷ��� ��û �����Ѵ�
			controller.execute(request, response);  //���������� ����� (������)
			
			//5�ܰ� : Ŭ���̾�Ʈ���� �˸��� ����� �����ش�
			response.sendRedirect(controller.getViewPage());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	//servlet�� �����ֱ� �޼��� ��, servlet�� �Ҹ��� �� ȣ��Ǵ� �޼����� destroy()�� 
	//stream ���� �ڿ��� �ݴ� ó���� ����
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
