package com.greeting;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿이란? 자바 클래스 중 오직 웹서버에서만 해석 밑 실행되어질 수 있는 클래스
public class HelloServlet extends HttpServlet {
	
	//이 메서드는 servlet이 태어난 직후에, 초기화 작업에 사용됨
	//init()메서드는 고양이(tomcat)와 같은 web container가 호출하며, 고양이로부터 받은 config객체를 통해 초기화한다
	//즉, servlet의 생성 및 생명주기 메서드의 호출자는 tomcat이다
	public void init(ServletConfig config) throws ServletException {
		
		String msg = config.getInitParameter("msg");
		System.out.println("init() 호출 시 넘겨받은 파라미터 정보는 " + msg);
		
		//jsp 내장 객체 웹 어플리케이션의 전역적 정보를 가진 객체 application
		ServletContext context = config.getServletContext();  //jsp에서의 appliction 내장객체였다!
		System.out.println(context.getRealPath("/"));
	}
	
	//서블릿이 일단 생성된 후, 초기화까지 마치면, 클라이언트의 요청을 처리할 준비가 된 것이며,
	//클라이언트의 요청을 처리하는 메서드가 바로, service 메서드이다
	//service 메서드가 요청을 처리하려면, 클라이언트의 요청 정보와 클라이언트에게 보낼 응답정보를 필요로하기 때문에
	//service() 메서드의 매개변수로 request, response객체가 전달되어야 한다(고양이가 함)
	//service()는 스크립틀릿 영역이었던 것이다
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트가 전송한 요청 정보 중 파라미터를 끄집어내서 출력해보자
		String id = request.getParameter("id");
		
		//클라이언트에 전송
		response.setContentType("text/html;charset=utf-8");  //지시영역이었던 것
		PrintWriter out = response.getWriter();
		out.print("당신이 전송한 파라미터는 " + id);
	}
	
	//servlet이 소멸할 때 호출되는 메서드
	//servlet이 보유한 자원을 반납할 때 (스트림, db close)등에 사용
	private void dist() {
		System.out.println("저 죽어요 ㅜㅜ");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("hello servlet do!!");
	}
}


