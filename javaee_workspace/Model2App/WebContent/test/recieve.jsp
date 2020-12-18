<%@ page contentType="text/html;charset=utf-8"%>
<%
	//메시지를 받아보자
	request.setCharacterEncoding("utf-8");
	String msg = request.getParameter("msg");

	//session.setAttribute("result", msg);

	//지금 요청과 관련된 요청 객체에 무언가를 담아보자
	//session과 request는 거의 쌍둥이 인데, 단지 살 수 있는 생명력에만 차이가 있다
	request.setAttribute("result", msg);  //result라는 이름으로 msg를 담는다

	//바로 응답해버리지 말고, 다른 jsp로 forwarding(==toss) 한다
	//server 상의 또다른 jsp(servlet)에 요청을 전달해보자.
	//RequestDispatcher == forwarding 객체
	RequestDispatcher dis = request.getRequestDispatcher("/test/result.jsp");
	dis.forward(request, response);  //forwarding 시작


	//client로 하여금 지정한 url로 다시 접속하라는 명령 -> 이 방법은 좋지 않다
	//response.sendRedirect("/test/result.jsp");
%>

