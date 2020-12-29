package test;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//Tomcat과 같은 요청이 들어오기도 이전 시점(서버를 가동하는 시점)의 이벤트를 감지할 수 있는
//ServletContextListener를 이용하여, ServletContext객체에 무언가를 작업해보자
public class MyContextListener implements ServletContextListener {

	@Override
	//어플리케이션이 초기화될 때 호출되는 메서드
	public void contextInitialized(ServletContextEvent sc) {
		System.out.println("어플리케이션이 톰캣 시작과 함께 가동");
		//application의 전역적 정보를 가진, session보다도 훨씬 오래 살아가는 servletContext에
		//비즈니스 객체들 (Service, DAO.. )을 올리자
		ServletContext context = sc.getServletContext();
		context.setAttribute("msg", "난 오래 살아");
		
		String obj = context.getInitParameter("contextConfigLocation");
		System.out.println("application 가동될 때 메모리에 올려질 bean들은 " + obj);
	}

	@Override
	//어플리케이션이 종료될 때 호출되는 메서드
	public void contextDestroyed(ServletContextEvent sc) {
		System.out.println("어플리케이션이 톰캣 종료과 함께 종료");
	}


}
