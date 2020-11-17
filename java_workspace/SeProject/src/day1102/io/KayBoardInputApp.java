/*입력스트림 처리는 파일에 국한된 기술이 아니다.
	즉, 실행중인 프로그램으로 데이터가 흘러들어온다면, 이 모든 것이 입력스트림이다.!
	따라서 이 예제에서는 파일을 대상으로 데이터를 읽는 것이 아니라,
	키보드를 대상으로 데이터를 읽어와보자!
	주의) 파일과는 달리, 키보드의 스트림은 자바에서 생성할 수 있는 것이 아니라, 
		이미 OS차원에서 스트림이 존재하므로, 
		자바는 단지 이미 존재하는 스트림을 얻어와 쓸 수 있을 뿐이다.*/

package day1102.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KayBoardInputApp {

	public static void main(String[] args) {
		/*
		//키보드 스트림은 이미 시스템적으로 존재하므로, 자바의 System 클래스에서 얻어오면 된다.
		InputStream is = System.in;  //표준 일벽 스트림(키보드 or 기타 입력도구에 대한 스트림)
			
		int data;
		try {  //shift + alt + z
			while(true) {
				data = is.read();  //1byte 읽기
				//read()의 특징 : 입력이 완료되지 않으멸, 무한 대기 상태로 머물러 있음.
				//즉, 입력이 되어야 read() 메서드 이후의 문장이 수행될 수 있다.
				System.out.print((char)data);  //표준 출력 스트림				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		
		/*----한글이 나오도록 문자로 처리----*/
		InputStream is = System.in;
		InputStreamReader reader = new InputStreamReader(is);
		int data;
		
		try {
			while(true) {
				data = reader.read();
				if(data == -1) {break;}
				System.out.print((char)data);				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
