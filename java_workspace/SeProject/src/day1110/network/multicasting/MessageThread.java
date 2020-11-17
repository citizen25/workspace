package day1110.network.multicasting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//�� ������� ��ȭ�� �������̹Ƿ�, ����� ��Ʈ���� ������ �ν��Ͻ��� �����ؾ��Ѵ�
//�Ʒ��� Ŭ������ ������� �����ϴ� ��������, �� �ν��Ͻ����� ���� �񵿱���(Asynronous)���� ������ �� �ִ�
public class MessageThread extends Thread{

	Socket socket;  //������ ������� ��ȭ�� ������ �����ؾ� ��Ʈ���� ���� �� ���̹Ƿ�
					//�����ڰ� �����Ǹ� �ش� ������ �μ��� �Ѱܹ���
	BufferedReader buffr;  //���
	BufferedWriter buffw;  //���ϱ�

	MultiServer multiServer;
	boolean flag = true;
	
	public MessageThread(MultiServer multiServer, Socket socket) {
		this.multiServer = multiServer;
		this.socket = socket;
		
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
	
	//�޼��� �ޱ�(û��)
	public void listen() {
		String msg = null;
		try {
			while(flag) {
				msg = buffr.readLine();
				
				//�ڵ� ��ɾ� �� �����ٴ� ���̸�.. ������ ó��
				if(msg.equals("exit")) {
					//1)���� ��ܿ��� �����ϰ�  2)flag�� false : thread dead
					multiServer.clientList.remove(this);  //���� ��ܿ��� ����
					flag = false;
					multiServer.area.append("������� ������ �� : "+multiServer.clientList.size()+"\n");
				}else {  //�ƴ� ���.. ���� ���� �״�� ó��
					multiServer.area.append(msg+"\n");
					send(msg);  //Ŭ���̾�Ʈ���� �ٽ� �������Ѵ� (������ �ǹ�)					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Ŭ���̾�Ʈ���� �޽��� ������
	public void send(String msg) {
		try {
			//���Ϳ� ����ִ� �޽��������常ŭ �ݺ��� �����ϸ鼭, .wirte, .flush �����ϸ� ��(��Ƽ ĳ����)
			for(int i=0; i<multiServer.clientList.size(); i++) {				
				MessageThread messageThread = multiServer.clientList.get(i);  //���Ϳ� ����ִ� �޽��������带 �ϳ��� ������
				messageThread.buffw.write(msg+"\n");
				messageThread.buffw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
