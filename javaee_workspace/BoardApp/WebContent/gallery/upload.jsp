<%@page import="java.io.File"%>
<%@page import="common.FileManager"%>
<%@page import="java.io.IOException"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page contentType="text/html; charset=utf-8"%>

<%
	/*클라이언트가 전송한 제목 텍스트 및 바이너리 파일을 서버의 특정 디렉토리에 저장해보자
	이런 업무를 업로드라고 한다*/

	request.setCharacterEncoding("utf-8");  //파라미터에 한글 깨지지 않도록 인코딩 처리
	//String msg = request.getParameter("msg");  //String 메시지 받기

	//이미지는 글씨가 아닌 바이너리파일이므로, request.getParameter로는 받을 수 없다
	//따라서 IO, 네트워크등의 처리를 해야하는데, 이 자체만으로도 하나의 개발 프로젝트일 것이다.
	//해결책) 누군가 만든 라이브러리를 사용한다 -> cor.jar 라는 Oreilly 출판사 제작 컴포넌트
	String saveDirectory = "C:/workspace/javaee_workspace/BoardApp/WebContent/data";
		//하드디스크의 물리적 full 경로를 명시해야한다
	int maxSize = 2*1024*1024;  //2M byte
	String encoding = "utf-8";
	
	//FileRenamePolicy policy : 업로드시, 동일한 파일을 업로드했을 때, 자동으로 이름을 부여한다
	//예) p.jpg, 1p.jpg, 2.jpg(파일명은 개발자가 주도하여 명명하므로, policy를 궅이 이용할 필요 없다)
	
	try{
		MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxSize, encoding);  //업로드 발생		
		//업로드 컴포넌트를 이용할 경우, 스트링 파라미터도 업로드 컴포넌스를 이용해야한다
		String msg = multi.getParameter("msg");
		out.print("님이 전송한 메시지는 " + msg);
		
		//업로드가 완료된 후(서버의 저장소에 파일이 존재하게 된 후) 해야할 일은?
		//파일명을 개발자가 정한 규칙으로 변경해야한다. 현재 시간의 밀리세컨드까지 구하자
		long time = System.currentTimeMillis();
		//구한 시간에 확장자를 붙이면 최종적으로 파일명이 만들어진다.
		out.print(time);
		
		//방금 업로드한 파일명 알아맞추기(업로드 컴포넌트가 알고있다)
		String ori = multi.getOriginalFileName("photo");
		out.print("당신이 업로드한 로컬 원래 파일명은 " + ori);
		
		String ext = FileManager.getExtend(ori);
		
		String filename = time + "." + ext;
		
		out.print("내가 조작한 파일명은 " + filename);
		
		File savedFile = multi.getFile("photo");
		savedFile.renameTo(new File(saveDirectory + "/" + filename));  //파일명 교체

		/*클라이언트에게  전송할 응답정보를 가진 객체*/
		//클라이언트의 브라우저로하여금, 지정한 URL로 재접속을 시도하게 만듬
		out.print("업로드 완료");
		
	}catch(IOException e){
		e.printStackTrace();  //로그에 에러 출력
		out.print("IO Error");  //서블릿 쓰레드 에러(servlet 클래스를 배워야 에러 잡을 수 잇다.)
	}

	
	
		
%>