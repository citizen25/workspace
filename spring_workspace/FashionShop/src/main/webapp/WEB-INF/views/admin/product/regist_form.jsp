<%@page import="com.koreait.fashionshop.model.domain.TopCategory"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List<TopCategory> topList = (List)request.getAttribute("topList");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../inc/header.jsp"%>
<style>
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
/* 드래그 관련 */
#dragArea {
	width: 100%;
	height: 300px;
	overflow: scroll;
	border: 1px solid #ccc;
	float: left;
}

.dragBorder {
	background: #ffffff;
}
.box {
	width: 100px;
	float: left;
	padding: 5px;
}
.box > img {
	width: 100%;
}
.close {
	color: red;
	cursor: pointer;
}

</style>
<script type="text/javascript">
	var uploadFiles = [];  //미리보기 이미지 목록
	var psize = [];  //유저가 선택한 사이즈를 담는 배열

	$(function(){
		CKEDITOR.replace("detail");

		//상위 카테고리 선택하면..
		$($("select")[0]).change(function(){
			//비동기 방식으로 서버에 요청하되, 순수 ajax 보다는 jquery ajax를 이용하자
			getSubList(this);
		});
	
		//드래그 롼경 이벤트
		$("#dragArea").on("dragenter", function(e) {  //드래그로 영역에 진입했을 때
			$(this).append("dragenter<br>");
			$(this).addClass(".dragBorder");
		});

		$("#dragArea").on("dragover", function(e) {  //드래그 영역 위에 있을 때
			//$(this).append("dragover<br>");
			e.preventDefault();  //여타 다른 이벤트를 비활성화 시키자
		});

		$("#dragArea").on("drop", function(e) {  //드래그 영역 위에서 이미지를 놓으면
			e.preventDefault();  //여타 다른 이벤트를 비활성화 시키자
			$(this).append("drop<br>");
			
			//자바 스트립트로 드래그된 이미지 정보를 구해와서, div 영역에 미리보기 효과
			var fileList = e.originalEvent.dataTransfer.files;  //드래그한 파일들에 대한 배열 얻기
			console.log(fileList);
			
			//배열안에 있는 이미지들에 대한 이미지 미리보기 처리
			for(var i=0; i<fileList.length; i++){
				uploadFiles.push(fileList[i]);  //fileList 안의 요소들을 일반 배열에 옮겨심기. 배열이 지원하는 여러 메서드들을 활용하기 위해(ex.indexOf)
				preview(uploadFiles[i], i);  //파일 요소 하나를 넘기기
			}
			
		});

		$("#dragArea").on("dragleave", function(e) {  //드래그로 영역에서 빠져나가면
			$(this).append("dragleave<br>");
			$(this).removeClass(".dragBorder");
		});
		
		//이미지 삭제 이벤트 처리
		$("#dragArea").on("click", ".close", function(e) {
			console.log(e);
			
			//대상 요소 배열에서 삭제
			//삭제 전에 uploadFiles라는 배열에 들어있는 file의 index를 구하자
			var f = uploadFiles[e.target.id];
			var index = uploadFiles.indexOf(f);  //파일 객체가 몇변째 들어있는지 추출
			
			//alert(e.target.id + " 클릭");
			uploadFiles.splice(index, 1);
			//대상 요소 삭제(시각적으로 삭제)
			$(e.target).parent().remove();
		});

		//체크박스 이벤트 구현 
		$("input[type='checkbox']").on("click", function(e){
			var ch = e.target;//이벤트 일으킨 주체컴포넌트 즉 체크박스
			var ch = $("input[name='size']");
			var len = $("input[name='size']").length;  //체크박스의 길이를 얻어 반복문에 이용
			
			psize = [];  //배열 초기화

			for(var i=0; i<len; i++) {
				//만일 체크가 되어있다면, 기존 배열을 모두 자우고, 체크된 체크박스 값만 배열에 넣자
				if($($(ch)[i]).is(":checked")) {
					psize.push($($(ch)[i]).val());
				}
				//console.log(i, "번째 체크박스 상태는", $($(ch)[i]).is(":checked"));
			}
			console.log("서버에 전송할 사이즈 배뎔의 구성은 ", psize);
		});	
			
	});
	
	//업로드 이미지 미리보기
	function preview(file, index) {
		//js로 이미지 미리보기를 구현하려면, FileReader를 이용하면 된다
		var reader = new FileReader();  //아직 읽을 대상 파일이 결정되지 않음
		//파일을 읽어들이면, 이벤트 발생기킴
		reader.onload = function(e) {
			//console.log(e.currentTarget.result);
			//console.log(reader.result);  //
			
			var tag = "<div class=\"box\">";
			tag += "<div class=\"close\" id=\"" + index + "\">X</div>";
			tag += "<img src=\"" + e.currentTarget.result + "\">";
			tag != "</div>";

			$("#dragArea").append(tag);
		};
		reader.readAsDataURL(file);  //지정한 파일을 읽는다(매개변수로는 파일이 와야함)
		
	}
	
	//비동기 방식으로 하위 카테고리 요청하기
	function getSubList(obj){
		//alert($(obj).val());

		$.ajax({
			url: "/admin/product/sublist",
			type: "get",
			data: {
				topcategory_id: $(obj).val()
			},
			success: function(result){
				//alert("서버로부터 받은 결과는 " + result);
				//server가 이미 json으로 전송해주었으므로 별도의 parsing이 필요 없다
				//기존 option 태그를 먼저 지우자
				$($("select")[1]).empty();
				$($("select")[1]).append("<option>하위카테고리 선택</option>");

				for(var i=0; i<result.length; i++){
					var subCategory = result[i];  //subcategory 1건에 대한 json 객체 얻기

					$($("select")[1]).append("<option value=\"" + subCategory.subcategory_id + "\">" + subCategory.name + "</option>");
				}
			}
		});
	}
	
	//사이즈 선택시 배열 재구성하기
	function setPsizeArray(){
		
		
	}	

	//상품 등록
	function regist(){
		//비동기 전송 시, 기존의 form을 이용할 수 있다
		//$("textarea").val(CKEditor.instances.detail.getData));

		var formData = new FormData($("form")[0]);  //<form> 태그와는 다르다. 전송할 때 파라미터들을 담을 수 있지만, 이 자체가 form 태그는 아니다
		
		//미리보기했던 이미지들은 file component와 되어있지 않기 때문에 전송 데이터에서 빠져있다
		//따라서 formData 전송 전에, 동적으로 file component화시켜 formData에 추가하자
		$.each(uploadFiles, function(i, file){  //java에서의 improved for문과 동일한 역할 (주로 collection에서 object를 꺼낼 때 편하게 사용)
			formData.append("addImg", file, file.name);  //첫번째 매개변수:<input type=file name=addImg> 에서 name, 두번째:업로드할 파일 객체, 세번째:파일명
			console.log(file.name);
		});
		
		formData.append("detail", CKEDITOR.instances["detail"].getData());  //폶데이터에 에디터의 값 추가하기

		for(var i=0; i<psize.length; i++) {
			formData.append("psize[" + i + "].fit", psize);
		}

		//formData.append("test", new Array("banana", "apple", "orange"));
		//== input type="ckeckbox" name="test" value="banana"
		//== input type="ckeckbox" name="test" value="apple"
		//== input type="ckeckbox" name="test" value="orange"

		//비동기 업로드
		$.ajax({
			url: "/admin/product/regist",
			data: formData,
			contentType: false,  //false일 경우, multipart/form-data로 간주 -> 이미지이기 때문에 get방식 말고 post방식으로 날려야 한다
			processData: false,  //false일 경우, query-string으로 전송되지 않음(get방식이 아니게 된다)
			type: "post",
			success: function(responseData) {
				var json = JSON.parse(responseData);  //String --> json 으로 parsing
				if(json.result == 1) {
					alert(json.msg);
					location.href = "/admin/product/list";
				} else {
					alert(json.msg);
				}
			}
		});
		
		
		//동기 방식 업로드
		/*$("form").attr({
			action: "/admin/product/regist",
			method: "post",
			enctype: "multipart/form-data"
		});
		$("form").submit();*/
	}
</script>
</head>
<body>
	<%@ include file="../inc/main_navi.jsp" %>
	<h1>상품 등록</h1>
	<div class="container">
		<form>
			<select>
				<option>상위 카테고리 선택</option>
				<%for(TopCategory topCategory : topList) { %>
				<option value="<%=topCategory.getTopcategory_id()%>"><%=topCategory.getName() %></option>
				<%} %>
			</select>
			<select name="subCategory.subcategory_id">
				<option>하위 카테고리 선택</option>
			</select>
			<input type="text" name="product_name" placeholder="상품명"> 
			<input type="text" name="price" placeholder="가격"> 
			<input type="text" name="brand" placeholder="브랜드"> 

			<!-- 파일 최대 4개까지 지원 -->
			<p>대표 이미지 : <input type="file" name="repImg"/></p>

			<div id="dragArea">
			</div>

			<!-- 지원 사이즈 선택 : size를 배열로 만들어서 서버에 보내기 위한 배열을 체크된 psize로 다시 만든다 -->
			<p>
				XS<input type="checkbox" name="size" value="XS">
				S<input type="checkbox" name="size" value="S">
				M<input type="checkbox" name="size" value="M">
				L<input type="checkbox" name="size" value="L">
				XL<input type="checkbox" name="size" value="XL">
				XXL<input type="checkbox" name="size" value="XXL">
			</p>
			
			<!-- color picker -->
			<p>
				<input type="color" name="color[0].picker" value="#cccccc"/>
				<input type="color" name="color[1].picker" value="#ffffff"/>
				<input type="color" name="color[2].picker" value="#000000"/>
				<input type="color" name="color[3].picker" value="#00ff00"/>
				<input type="color" name="color[4].picker" value="#0000ff"/>
				<input type="color" name="color[5].picker" value="#ff0000"/>
			</p>
			<textarea id="detail" name="detail" placeholder="상세 정보" style="height: 200px"></textarea>


			<input type="button" onclick="regist()" value="글등록"> 
			<input type="button" value="목록보기" onClick="location.href='/client/notice/list'">
		</form>
	</div>

</body>
</html>