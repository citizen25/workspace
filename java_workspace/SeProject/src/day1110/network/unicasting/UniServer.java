package day1110.network.unicasting;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UniServer extends JFrame{
	JTextField t_port;
	JButton bt;
	JPanel p_north;
	JTextArea area;
	JScrollPane scroll;
	
	ServerSocket server;
	int port=7777;
	
	Thread thread;  //메인쓰레드 대신 접속자를 감지하게될 쓰레드 (accept() 메서드 때문에)
	BufferedReader buffr;  //듣기
	BufferedWriter buffw;  //말하기
	
	public UniServer() {
		t_port = new JTextField(Integer.toString(port), 10);
		bt = new JButton("서버 가동");
		p_north = new JPanel();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		//조립
		p_north.add(t_port);
		p_north.add(bt);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		//가동 버튼과 리스너 연결
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thread = new Thread() {
					public void run() {
						startServer();						
					}
				};
				thread.start();  //Runnable로 진입시키자
			}
		});

		setVisible(true);
		setBounds(600, 200, 300, 400);  //x, y, width, height
	}
	
	//서버 가동
	public void startServer() {
		try {
			server = new ServerSocket(Integer.parseInt(t_port.getText()));  //서버소켓 생성
			area.append("서버 준비\n");
			//서버는 여러명의 접속자를 감지해야한다면, 각 접속자마다 비동기적으로(독립적으로) 상관없이 
			//대화를 주고 받는 주체는 쓰레드의 인스턴스로 처리하자
			while(true) {
				//서버 가동하기
				Socket socket = server.accept();  //접속자 감지와 동시에 대화용 소켓 반환
				area.append("접속자 발견\n");

				//대화용 쓰레드를 생성하고, 소켓을 넘겨주자
				MessageThread messageThread = new MessageThread(this, socket);
				messageThread.start();  //Runnable로 진입
				
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	//main thread는 절대 1.무한루프 2.대기 상태에 빠뜨려서는 안된다 
	public static void main(String[] args) {
		new UniServer();
	}

}
