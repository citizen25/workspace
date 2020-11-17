/*채팅 메시지를 보내지 않더라도, 채팅에 참여하는 모든 사람이 보낸 메시지를 수신하려면
	무한 루프로 실행되면서 스트림을 읽을 쓰레드가 필요하다*/

package day1110.network.multicasting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientThread extends Thread{
	MultiClient multiClient;
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	
	boolean flag = true;
	
	public ClientThread(MultiClient multiClient, Socket socket) {
		this.multiClient = multiClient;
		this.socket = socket;
		
		//접속이 성공되었으므로, 스트림을 얻을 수 있다
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void run() {
		listen();
	}

	//서버가 보낸 메시지 듣기
	public void listen() {
		String msg = null;
		try {
			while(flag) {
				msg = buffr.readLine();
				multiClient.area.append(msg+"\n");  //대화 기록하기				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//서버에 메세지 보내기(출력)
	public void send(String msg) {
		try {				
			buffw.write(msg+"\n");
			buffw.flush();  //남아있는 데이터 없도록 버퍼 비우기
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
