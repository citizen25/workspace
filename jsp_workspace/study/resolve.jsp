<%@ page contentType="text/html;charset=utf-8"%>
<%!
 //선언부
 int k = 7;
%>
<%
 //스크립틀릿 영역
 int x = 3;
 String driver="oracle.jdbc.driver.OracleDriver";
 String user="user1104";
 String password="user1104";
 String url="jdbc:oracle:thin:@localhost:XE:1521";
 
 out.print(k);

 /*
 클라이언트의 브라우저 요청이 온다면..
  tomcat은 정적 자료(html, image, mp3 ...)가 들어오면 
  자면서 그대로 보내주기만 한다
  그러나 jsp가 들어오면 깨어나서 <%%>실행부를 실행한다
  그렇게 실행한 것을 종합해서 html로 보내준다

  자바스크립트는 브라우저에서 실행이 되는 것이다
 */

%>
<!DOCTYPE html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <script>
   //자바스크립트는 오라클 연동 능력이 없다
   //자바스크립트는 능력이 없어서가 아니라, 실행 위치가 클라이언트의
   //브라우저이기 때문에 보안이 불가능하다
   //자바스크립트를 가리켜 클라이언트 스크립트라 한다
   //  VS jsp, php, asp와 같은 스크립트 언어는 
   //  서버에서 실행되기 때문에 서버스크립트라 한다
   var a=7;
  </script>
  <title>Document</title>
 </head>
 <body>
  나는 <%="java"%> 개발자다
 </body>
</html>
