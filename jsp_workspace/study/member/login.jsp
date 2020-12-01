<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%
	//request와 response 객체 이용한 로그인 처리
	//client가 전송한 id, pass 파라미터를 받아와 처리해보자


	//request객체를 이용하면, client의 요청을 처리할 수 있다
	String id = request.getParameter("id");  //client가 요청시 전송한 parameter값을 얻는 메서드
		//매개변수로는 파라미터 변수명을 명시.
		//만일 html 이용할 경우, 컴포넌트에 부여된 name값을 명시
	String pass = request.getParameter("pass");
	out.print("현재 클라이언트의 요청 방식은 " +request.getMethod());
	out.print("<br>");
	out.print("클라이언트가 전송한 id는 " + id);
	out.print("<br>");
	out.print("클라이언트가 전송한 pass는 " + pass);

	//드라이버 로드
	//driver를 포함한 모든 jar 파일은 javaEE기반의 스펙을 따라서 위치시켜야 한다
	//스펙을 따르자면, class파일과 jar는 WEB-INF라는 대문자로 된 보안된 디렉토리에 위치시켜야 한다
	//클래스의 위치는 WEB-INF/classes, jar는 WEB-INF/lib에 둬야 한다
	Class.forName("oracle.jdbc.driver.OracleDriver");

	//접속
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "user1104";
	String password = "user1104";
	Connection con = DriverManager.getConnection(url, user, password);
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	if(con == null){
		out.print("접속 실패");
	}else{
		out.print("접속 성공");

		//쿼리문 수행
		String sql = "select * from shop_member where mid=? and pass=?";
		pstmt = con.prepareStatement(sql);  //쿼리 준비
		pstmt.setString(1, id);
		pstmt.setString(2, pass);
		rs = pstmt.executeQuery();

		if(rs.next()){  //회원이 있다면
			out.print("로그인 성공");
		}else{
			out.print("로그인 정보가 올바르지 않습니다.");
		}
	}

	if(rs != null){
		rs.close();
	}
	if(pstmt != null){
		pstmt.close();
	}
	if(con != null){
		con.close();
	}
%>