package day1110.network.echo;

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

public class EchoServer extends JFrame{
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
	
	public EchoServer() {
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
		setDefaultCloseOperation(EXIT_ON_CLOSE);  //윈도우 창 닫기 + 프로세스 종료
		setVisible(true);
		setBounds(600, 200, 300, 400);  //x, y, width, height
	}
	
	//서버 가동
	public void startServer() {
		try {
			server = new ServerSocket(Integer.parseInt(t_port.getText()));  //서버소켓 생성
			area.append("서버 준비\n");
			//자바는 thread 기반이므로, 지금까지 메인 실행부라 불렸던 실행 주체도 사실은 시스템에 의해 생성된 thread였다
			//하지만, main thread는 개발자가 생성하는 일반thread와는 하는 역할에 차이가 있다
			//main thread는 프로그램을 운영해주는 역할. 특히, 그래픽 처리, 이벤트 처리까지 담당하므로, 절대 아래의 업무는 금지한다
			//1) 무한루프에 빠뜨리는 업무
			//2) 대기 상태에 빠지게 하는 업무 : accept(), 스트림의.read() 등등
			//   cf) 안드로이드에서는 main thread의 1,2번 시도자체를 에러로 본다 
			//결론 : 별도의 thread를 생성하여 처리하자
			
			//서버 가동하기
			Socket socket = server.accept();  //접속자 감지와 동시에 대화용 소켓 반환
			area.append("접속자 발견\n");
			
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			listen();  //듣기 시작
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//메세지 받기(청취)
	public void listen() {
		String msg = null;
		try {
			while(true) {
				msg = buffr.readLine();
				area.append(msg+"\n");
				send(msg);  //클라이언트에게 다시 보내야한다 (서버의 의무)
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//클라이언트에게 메시지 보내기
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//main thread는 절대 1.무한루프 2.대기 상태에 빠뜨려서는 안된다 
	public static void main(String[] args) {
		new EchoServer();
	}

}
