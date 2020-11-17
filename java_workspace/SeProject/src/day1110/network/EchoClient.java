/*에코 서버에 접속하여 메시지를 주고받을 클라이언트 작성*/

package day1110.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
	Socket socket;  //대화용 소켓
	String ip="localhost";
	int port=8989;
	Thread thread;
	
	public EchoClient() {
		//소켓은 생성시 접속을 시도한다
		//따라서, 소켓의 인스턴스가 얻어졌다는 것은, 이미 서버와의 접속이 된 상태이다
		try {
			socket = new Socket(ip, port);  //접속
			System.out.println("접속 성공");
			
			//받는 용. 즉, 듣는 용 == "야"하고 말한게 서버에 갔다가 다시 돌아오는 것을 듣는 것 == 입력
			InputStream is = socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(is);
			BufferedReader buffr = new BufferedReader(reader);
			
			//보내는 용. 즉, 말하는 용 == "야"하고 클라이언트에서 서버한테 말하는 것 == 출력
			OutputStream out = socket.getOutputStream();
			OutputStreamWriter writer = new OutputStreamWriter(out);
			BufferedWriter buffw = new BufferedWriter(writer);
			
			thread = new Thread() {  //1초마다 서버로 "야호"가 날아가도록 thread 걸어준다
				public void run() {
					//얻어진 스트림을 이용하여 서버에 메시지 보내기
					while(true) {
						//write와 flush는 세트로 생각한다
						try {
							buffw.write("야호\n");  //서버에 메시지 출력. buffer의 끝을 알리는 개행을 반드시 넣어야한다				
							buffw.flush();  //버퍼처리된 출력 스트림의 경우, 스트림 안의 데이터를 모두 비워버리는 것.
							Thread.sleep(1000);
						} catch (IOException e) {
							e.printStackTrace();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
			
			thread.start();  //Runnable 영역으로 밀어넣기 (JVM에게 맡기기)
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EchoClient();
	}
}
