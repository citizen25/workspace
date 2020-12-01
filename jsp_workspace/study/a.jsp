<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <title>Document</title>
  <script>
   function getMsg(){
	return "<%="안녕"%>";
   }
   //alert(getMsg());
   <%
    out.print(getMsg()+" 하세요");  //에러나는 이유?
	  //실행되는 장소가 script는 클라이언트 영역,
	  //<%%>는 server이기 때문
   %>
  </script>
 </head>
 <body>
  
 </body>
</html>
