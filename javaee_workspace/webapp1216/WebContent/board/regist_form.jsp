<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

input[type=text], select, textarea {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}

input[type=button] {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=button]:hover {
	background-color: #45a049;
}

.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
		var bt_regist = $("input[type='button']")[0];
		
		$(bt_regist).click(function(){
			regist();
		});
	});
	
	function regist(){
		$("form").attr({
			action:"/board/regist"  //서블릿에게 요청
			, method:"post"
		});
		$("form").submit();
	}

</script>

</head>
<body>

	<h3>글 등록 폼</h3>

	<div class="container">
		<form>
			<input type="text" name="title" placeholder="Title..">
			<input type="text" name="writer" placeholder="Writer..">
			<textarea name="content" placeholder="Write something.." style="height: 200px"></textarea>
			<input type="button" value="글 등록">
			<input type="button" value="목록" onClick="location.href='list.jsp'">
		</form>
	</div>

</body>
</html>
