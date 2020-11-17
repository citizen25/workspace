/*소켓이란?
	일상생활에서는 전구 드에 꼽는 접점 도구이다
	역할?
		전기 지식이 없어도 전구를 동작시킬 수 있다 (일상생활)
		== 네트워크 지식이 없어도, 네트워크 제어 프로그래밍이 가능하게 하는 것 (프로그래밍 분야)
		따라서, 사실상 프로그래밍 언어에서 개발자가 제어하는 대상은 스트림인 것 뿐이다
	
	소켓은 자바 언어만 지원한다 (X)
		현존하는 대부분의 응용 어플리케이션에서 지원하고 있다(컴파일 기반: c, java, c#, python(인터프리터지만))*/

package day1110.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	int port=8989;  //클라이언트를 받아들일 포트번호. 이 포트번호에 의해 다른 네트워크 프로세스와 구분될 수 있다. ex)오라클 1521, mysql 3306
	ServerSocket server;  //대화용 소켓이 아니라, 접속자를 감지하고 접속자가 발견되면 대화용 소켓을 반환해주는 객체
						  //마치 전화기의 벨이 울리면 그 이후부터 전화를 받고 대화하는 것과 비슷(전화 받는용 소켓)
	
	public EchoServer() {
		try {
			server = new ServerSocket(port);  //서버소켓 생성(아직 가동은 아님)
			Socket socket = server.accept();  //서버가동 및 클라이언트 접속 기다림(접속이 들어올 때까지 무한대기 한다. 지연발생)
			System.out.println("접속자 발견");
			//개발자는 반환받은 소켓으로부터 통신에 필요한 입출력 스트림은 얻을 수 있다
			//이때 개발자는 네트워크 하부에 대한 아무런 지식이 없이 그냥 스트림 처리만 하면 알아서 원격지의 대화상대와
			//통신이 가능하며 이 모든것들은 소켓이 알아서 해주는 거다
			InputStream is = socket.getInputStream();  //바이트 기반의 입력스트림이므로 영문만 해석 가능한 상태
			InputStreamReader reader = new InputStreamReader(is);
			BufferedReader buffr = new BufferedReader(reader);
			
			//int data;
			String data;
			while(true) {
				//data = reader.read();  //1byte 읽기(소켓과 연결된 스트림으로부터)
				data = buffr.readLine();  //한줄씩 읽기(소켓과 연결된 스트림으로부터)
				//System.out.print((char)data);				
				System.out.print(data);				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EchoServer();
	}

}
