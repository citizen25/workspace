package com.koreait.restproject.android;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//소켓을 가지고, 입력과 출력을 수행하되, 다른 객체와 독립적으로 수행될 수 있어야 하므로 
//쓰레드로 정의한다!!
public class ChatThread extends Thread{
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	boolean flag=true; //쓰레드 가동 여부를 결정하는 논리값, 이 쓰레드를 죽이고 싶다면 false로 준다!!
	
	public ChatThread(Socket socket) {
		this.socket=socket;
		
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//메시지 청취
	public void listen() {
		String msg=null;
		try {
			msg= buffr.readLine();
			send(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//메시지 전송
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(flag) {
			listen();
		}
	}
}
