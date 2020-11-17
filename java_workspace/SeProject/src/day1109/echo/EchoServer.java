/*최종 목표는 채팅(멀티캐스팅 구현)이지만, 일단 기초인 Echo System을 먼저 학습한다*/

package day1109.echo;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	ServerSocket server;  //대화용 소켓이 아닌, 접속자 감지용 서버측 소켓
	int port=9999;  //port 1~1024번까지는 이미 시스템이 점유하므로, 사용불가
	
	public EchoServer() {
		//서버소켓을 이용하여, 접속자를 받아보자
		try {
			server = new ServerSocket(port);  //서버소켓 생성
			System.out.println("서버 소켓 생성 완료");
			
			Socket socket = server.accept();  //접속자가 발견될 따까지 블락상태로 기다림
			System.out.println("접속자 발견");
			
			//반환받은 소켓을 이용하면, 현재 접속자에 대한 정보를 구할 수 있으며, 특히 ip를 조사해보자
			InetAddress inet = socket.getInetAddress();  //인터넷 주소정보를 가진 객체
			String ip = inet.getHostAddress();  //ip추출
			System.out.println("접속한 클라이언트의 아이피는 "+ip);
			
			//클라이언트가 보낸 메시지 받기 
			//(메시지를 받는 것은 실행중인 프로그램으로 데이터가 들어오는 것이므로 입력스트림으로 처리해야 한다)
			InputStream is = socket.getInputStream();  //바이트 기반의 입력스트림(한글은 깨짐. 영문으로 하자)
			int data;
			while(true) {
				data = is.read();  //1byte 읽어들임
				System.out.print((char)data);				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EchoServer();
	}
}
