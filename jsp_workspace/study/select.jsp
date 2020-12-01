<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <title>Document</title>
 </head>
 <body>
  <select>
   <%for(int i=2020; i>=1930; i--){%>
    <option><%=i%></option>
   <%}%>
  </select>
 </body>
</html>
