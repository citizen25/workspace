//웹 상의 모든 클라이언트의 요청을 받고, 응답을 전담하는 컨트롤러 정의

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

	FileReader fis;  //controller mapping 설정 파일을 읽기 위한 stream
	JSONObject controllerMap;  //controller의 정보들이 들어있는 map
	JSONObject viewMap;
	
	public void init(ServletConfig config) throws ServletException {
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");
		String realPath = config.getServletContext().getRealPath(contextConfigLocation);
		System.out.println(realPath);
		
		try {
			fis = new FileReader(realPath);
			JSONParser jsonParser = new JSONParser();
			
			//파싱
			JSONObject json = (JSONObject)jsonParser.parse(fis);

			//데이터에 접근
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
	
	//1단계 : doXXX형 메서드를 정의하여 요청을 받자
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  //들어오는 모든 request에 대한 encoding
		
		//----2단계 : 요청을 분석한다----
		String uri = request.getRequestURI();  //client가 요청 시 사용한 uri자체가 요청의 구분값으로 사용될 수 있다
		
		//if문을 대신할 "구조화"된 데이터를 선택하자(xml, json, properties) -> 오늘은 json으로
		String controllerName = (String)controllerMap.get(uri);
		System.out.println("지금 들어온 요청을 처리할 controller class는 " + controllerName);
		
		//동생 하위 Controller의 이름을 알았으니, instance를 만들고, execute(), getResultView()를 호출
		Class controllerClass = null;
		Controller controller = null;
		try {
			controllerClass = Class.forName(controllerName);  //String 즉, 문자열로 지정한 클래스에 대한 실제 클래스 반환
			controller = (Controller)controllerClass.newInstance();  //하위 Controller의 instance 생성
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		//3단계 : 일시키기
		controller.execute(request, response);
		//하위 controller로부터 넘겨받은 view에 대한 정보를 이용하여 client에 알맞는 view를 보여주자
		String resultKey = controller.getResultView();
		System.out.println("동생 controller에게 넘겨받은 키 값은 " + resultKey);
		
		//동생 controller로부터 넘겨받은 key값을 이용하여 실제 페이지를 검색하고,
		//그 결과를 이용하여 client가 보게될 페이지를 보여주자
		String viewPage = (String)viewMap.get(resultKey);  //jsp 반환
		
		//응답 시, sendRedirect로 처리해야할 경우가 있고, (글 작성 후, list등 전혀 다른 페이지로 재접속을 시도하게 할 때)
		//때로는 forwarding 처리해야할 경우가 있다 (데이터를 유지하고 싶을 때)
		if(controller.isForward()) {  //데이터 유지할 때
			RequestDispatcher dis = request.getRequestDispatcher(viewPage);
			dis.forward(request, response);  //응답 없이, 서버 상의 또 다른 자원으로 요청을 전달			
		} else {  //client로 하여금 새롭게 접속을 시도하게할 경우
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
