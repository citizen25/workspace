/*파일과 관련된 유용한 기능을 모아놓는 클래스*/
package common.file;

import java.io.File;

public class FileManager {
	//확장가만 추출하기
	public static String getExtend(String path) {
		
		int lastIndex = path.lastIndexOf(".");
		String ext = path.substring(lastIndex+1, path.length());
		
		return ext;
	}
	
	//파일 삭제 : 호출자는 삭제하고 싶은 파일의 경로를 넘긴다
	public static boolean deleteFile(String path) {
		File file = new File(path);
		return file.delete();
	}
	
	/*미리 단위 테스트 해보기 위한 main*/
	/*
	public static void main(String[] args) {
		String filename = "C:\\fd\\asd\\sf\\ds\\지난.영럼.놀라갓다너.사진.jpg"; 
		getExtend(filename);
	}
	*/
}
