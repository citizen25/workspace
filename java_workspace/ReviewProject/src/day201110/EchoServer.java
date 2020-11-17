package day201110;

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
	int port = 7777;

	Thread thread;
	BufferedReader buffr;
	BufferedWriter buffw;

	public EchoServer(){
		t_port = new JTextField(Integer.toString(port), 10);
		bt = new JButton("서버 가동");
		p_north = new JPanel();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		p_north.add(t_port);
		p_north.add(bt);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll);

		bt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				thread = new Thread(){
					public void run(){
						startServer();
					}
				};
				thread.start();
			}
		});

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(600, 200, 300, 400);
	}

	public void startServer(){
		try{
			server = new ServerSocket(Integer.parseInt(t_port.getText()));
			area.append("서버 준비\n");

			Socket socket = server.accept();
			area.append("접속자 발견\n");

			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			listen();
		}catch(NumberFormatException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void listen(){
		String msg = null;
		try{
			while(true){
				msg = buffr.readLine();
				area.append(msg + "\n");
				send(msg);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void send(String msg){
		try{
			buffw.write(msg + "\n");
			buffw.flush();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		new EchoServer();
	}

}



































