<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table{
		width: 70%;
	}
	td{
		border: 2px solid #cccccc;
	}
	td input, textarea{
		width: 97%;
		font-size: 1em;
	}
	textarea{
		height: 150px;
		
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
		$($("button")[0]).click(function(){  //등록
			regist();
		});
		$($("button")[1]).click(function(){  //목록
			location.href = "list.jsp";
		});
	});
	
	//폼 양식을 서버에 전송하자
	function regist(){
		$("form").attr({
			method:"post"
			, action:"regist.jsp"
		});					
		$("form").submit();
	}
	
</script>
</head>
<body>
	<form>
		<table align="center">
			<tr>
				<td><input name="writer" type="text" placeholder="작성자 입력"></td>
			</tr>
			<tr>
				<td><input name="title" type="text" placeholder="제목 입력"></td>
			</tr>
			<tr>
				<td><textarea name="content"></textarea></td>
			</tr>
			<tr>
				<td>
					<button type="button">등록</button>
					<button type="button">목록</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>