/*지난주에는 문서파일(영문)과 바이너리 파일(사진) 등을 복사해보았다.
	하지만, 문서파일의 경우 한글이 섞여있을 때 어떤 결과가 나오는지 테스트해본다.
	
스트림의 유형
 	스트림의 기본은 1byte씩 처리하는 바이트 기반의 스트림이다.
 	2byte의 스트림을 처리하는 패키지가 따로 있다.*/

package day1102.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MemoCopy {
	FileInputStream fis;
	FileOutputStream fos;
	
	FileReader reader;  //파일을 대상으로 한 문자 기반의 입력스트림
	FileWriter writer;  //파일을 대상으로 한 문자 기반의 출력스트림
	
	String path="C:/workspace/java_workspace/SeProject/res/data/test.txt";
	String path2="C:/workspace/java_workspace/SeProject/res/data/test2.txt";

	public MemoCopy() {
		try {
			//fis = new FileInputStream(path);
			//fos = new FileOutputStream(path2);
			reader = new FileReader(path);
			writer = new FileWriter(path2);
			
			//한 바이트씩 읽어들이면서 영문과 한글이 어떻게 되는지 관잘해보자.
			//결론 : FileInputStream은 바이트 기반의 스트림이므로, 1byte 씩만 해석할 수 있다.
			//    따라서 한글의 경우, 2byte로 구성되어 있으므로, 복사는 성공하겠으나,
			//    스트림 상에서 흐른 데이터를 한글로 보고자할 때는 깨져서 보인다.
			int data;
			while(true) {
				data = reader.read();
				if(data == -1) {break;}
				System.out.println((char)data);
				writer.write(data);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new MemoCopy();
	}

}
