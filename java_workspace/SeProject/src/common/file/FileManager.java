package common.file;

public class FileManager {
	//파일명 구하기 : 매개변수로 파일의 경로를 넘겨받아 파일명만 추출한다
	public static String getFilename(String path) {
		int lastIndex = path.lastIndexOf("/");  //마지막에 위치한 /(슬래시)의 인덱스 구하기
		return path.substring(lastIndex+1, path.length());
	}
	
	//확장자 구하기 : 매개변수로 파일명을 넘겨받아 확장자를 추출한다
	public static String getExtend(String filename) {
		String[] str = filename.split("\\.");  //.(점)을 기준으로 문자열 분리. 분리 후에는 배열이 반환됨. 특수문자 "."은 앞에 역슬래시 두개를 붙여야 한다
		return str[1];  //두번째 칸이 확장자임
	}
	
	//확장자 구하기 업데이트 : 마지막 점을 기준으로 가져와야 문제가 없다
	public static String getExtend2(String filename) {
		//마지막 점의 위치 구하기. lastIndexOf 메서드 사용하자
		
		int lastIndex = filename.lastIndexOf(".");
		
		return filename.substring(lastIndex+1, filename.length());
			//마지막 점 다음 문자부터 가져와야 하므로, +1 한다
	}
	/*
	public static void main(String[] args) {
		String filename = getFilename("http://3.bp.blogspot.com/-PTW4W1OlYIw/Wn7idkhYXTI/AAAAAAAAfIY/PAyn3Q1OwZQZxDrmshICJckNCI93AD6_QCK4BGAYYCw/s1600/ironman-3.jpg");
		
	}
	*/
	
}
