//MVC 패턴으로 개발하다보면, 늘어나는 컨트롤러에 대해 일일이 mapping과정을 진행해야한다
//이때 너무 많은 mapping은 관리하기가 까다롭다. --> 유지보수성이 떨어진다
//현실과 유사하게, application에서도 대형 업무 처리 시, client의 요청을 곧바로 해당 controller에게
//처리하게 하지 않고, 중간에 main controller를 두고, 모든 요청을 이 main controller에서 처리하여
//적절한 하위 controller에게 전담시키는 방식을 이용한다
//이 controller는 web applictaion의 모든 요청을 1차적으로 받는다. (그 다음에 분배)
//요청이 get인지 post인지 모른다..

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

	//init은 servlet의 생명주기에서, 최초 접속자에 의해 tomcat이 instance를 생성하며,
	//이와 동시에 초기화 메서드로서 호출된다
	public void init(ServletConfig config) throws ServletException {

		//getRealPath는 jsp의 내장객체 중 application에 대한 정보를 갖는
		//application 내장 객체에서 지원함
		ServletContext context = config.getServletContext();
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");
		String savePath = context.getRealPath(contextConfigLocation);
		
		System.out.println(savePath);
		
		try {
			fis = new FileInputStream(savePath);
			props = new Properties();
			props.load(fis);  //stream과 properties 연결
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
	
	//get or post 상관없이, 모든 요청을 이 메서드에서 처리하자
	//상위 컨트롤러와 하위 컨트롤러 간의 업무 흐름
	//1. 상위 컨트롤러가 요청을 받는다
	//2. 요청을 분석하여, 알맞은 하위 컨트롤러에 요청 전달
	//3. 하위 컨트롤러는 알맞은 로직 객체에게 일 시킨다
	//4. 결과가 있을 때는 그 결과를 저장
	//5. 알맞는 결과를 보여준다
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  //파라미터에 대한 인코딩
			//인코딩이 된 것을 하위에게 주기 위해서, 상위에서 한번에 해버린다

		//1단계 : 상위 컨트롤러가 요청을 받는다
		System.out.println("제가 요청을 받았어요 ㅜㅜ");
		
		//클라이언트가 영화를 원하면? -> 영화 담당 컨트롤러에게 전가
		//클라이언트가 혈액형을 원하면? -> 혈액형 담당 컨트롤러에게 전가
		
		//2-1단계 : 요청을 분석한다
		//글쓰기?, 삭제?, 혈액형?, 영화?, .... 어떤건지 알아맞추기
		//클라이언트가 원하는 것이 뭔지 알아야한다
		//해답은 클라이언트 요청 자체에 있다. 즉, 요청시 사용된 uri가 곧 요청 구분값이다
		//URL: http://localhost:9999/movie.do
		//URI: movie.do
		String uri = request.getRequestURI();
		System.out.println("지금 들어온 요청은 " + uri);
		
		Controller controller = null;
		
		String className = null;
		
		//if문을 대용할 수 있는 외부파일을 사용해서 className을 해결하자
		//properties 객체를 이용하여 key값으로 메모리에 올려질 컨트롤러의 이름을 value로 반환받자
		className = props.getProperty(uri);
		
		try {
			Class controllerClass = Class.forName(className);  //클래스 로드
			//인스턴스 생성
			controller = (Controller)controllerClass.newInstance();
			
			//2-2단계 : 알맞은 하위 컨트롤러에 요청 전달한다
			controller.execute(request, response);  //다형적으로 실행됨 (다형성)
			
			//5단계 : 클라이언트에게 알맞은 결과를 보여준다
			response.sendRedirect(controller.getViewPage());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	//servlet의 생명주기 메서드 중, servlet이 소멸할 때 호출되는 메서드인 destroy()에 
	//stream 등의 자원을 닫는 처리를 하자
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
