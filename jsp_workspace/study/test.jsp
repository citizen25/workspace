<%
/*JSP란 Java Server Page (java가 server에서 실행되는 형태)

	JSP문서의 구성 요소
	1. 지시영역 : @ 지시어를 사용하여 현재 페이지에 대한 
		인코딩, 임포드, 파일 형식 등 설정 정보를 선언하는 영역
		(page 지시자, include 지시자 등의 지시자를 지원함)
	2. 선언부 : 멤버변수와 멤버 메서드를 작성할 수 있는 영역
		(!표시한 영역)
	3. 스크립틀릿
	4. 표현식
--------------------------------------------------*/
%>

<%@ page contentType="text/html;charset=utf-8"%>
<%!
	//멤버 메서드 정의
	public String getMsg(){
		return "hi";
	}
%>
<%
	//자바의 로직을 작성
	//기존 javaSE코드를 작성하면 됨
	/*
	for(int i=0; i<10; i++){
		out.print("jsp 실행 결과 출력\n");
	}
	*/
	out.print(getMsg());
%>
<%=getMsg()%>




