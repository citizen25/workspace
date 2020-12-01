<%@ page contentType="text/html;charset=utf-8"%>
<%
	/*jsp는 자바의 문법을 따르므로, 별도로 공부할 사항이 없다
	jsp에서만 지원하는 내장객체(Built-In Object)를 학습한다
	또한 내장객체는 이미 내부적으로 인스턴스가 지원되면서
	해당 인스턴스의 변수명까지 이미 정해진 상태이므로 그냥 사용하면 된다

		1. request : 클라이언트의 요청 정보를 갖는 객체
		2. response : 클라이언트에게 보낼 응답 정보를 가진 객체
		3. out : jsp에 출력을 담당하는 객체
		4. session : 정보를 세션 범위에서 유지하기 위해 지원되는 객체
			로그인 정보 처리시 사용할 예정
		5. application : 정보를 어플리케이션 범위에서 유지하기 위해 지원되는 객체
		6. page : jsp를 표현하는 객체
		7. config : jsp의 설정 정보를 보유한 객체
		8. exception : jsp에서 발생한 예외정보를 보유한 객체
	*/
%>

