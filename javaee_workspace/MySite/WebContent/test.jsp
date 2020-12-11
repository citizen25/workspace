<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ page import="java.sql.*, javax.sql.*, java.io.*, javax.naming.InitialContext, javax.naming.Context"%>
</head>
<body>
	<h1>JDBC JNDI Resource Test</h1>
	<%
	
	/*
	JNDI란? Java Naming Directory Interface
		어떤 정보를 프로그래밍 언어인 자바코드에 넣지 말고,
		외부의 xml과 같은 자원으로 관리하는 방법
		즉, 자바코드 안에 설정정보를 넣지 말고, 코드 밖으로 빼서 유지 관리 하는 것
	*/
	
	InitialContext initCtx = new InitialContext();  //검색 객체
	
	DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/myoracle");
		//"java:comp/env/"는 정해진 것이므로 외우자
		
	Connection conn = ds.getConnection();  //Connection Pull로 부터 하나의 Connection을 얻는 작업
		//새로운 접속이 아니라, 이미 풀에 존재하는 접속 객체를 대여하는 것
	Statement stmt = conn.createStatement();
	ResultSet rset = stmt.executeQuery("select * from board");
	while (rset.next()) {
		out.println("title==" + rset.getString("title") + "<br>");
	}
	rset.close();
	stmt.close();
	conn.close();  //여기서 Connection Pull로 다시 돌려 보낸다
	initCtx.close();
	%>
</body>
</html>