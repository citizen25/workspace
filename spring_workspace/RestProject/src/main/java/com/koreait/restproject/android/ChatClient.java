package com.koreait.restproject.android;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient extends JFrame{
	JPanel p_north; 
	JTextField t_ip;
	JTextField t_port;
	JButton bt_con;
	JTextArea area;
	JScrollPane scroll;
	JTextField t_input;
	Socket socket;
	ClientThread clientThread;
	
	public ChatClient() {
		p_north = new JPanel();
		t_ip = new JTextField("172.30.1.28", 10);
		t_port = new JTextField("9999", 6);
		bt_con = new JButton("접속");
		area = new JTextArea();
		scroll= new JScrollPane(area);
		t_input = new JTextField(25);
		
		p_north.add(t_ip);
		p_north.add(t_port);
		p_north.add(bt_con);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		add(t_input, BorderLayout.SOUTH);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				stopClient();
			}
		});
		
		bt_con.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connectServer();
			}
		});
		
		t_input.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) { //엔터치면 전송
					clientThread.send(t_input.getText());
					t_input.setText("");//값 다시 비우기
				}
			}
		});
		
		setSize(300, 400);
		setVisible(true);
	}
	public void connectServer() {
		try {
			socket =new Socket(t_ip.getText(), Integer.parseInt(t_port.getText())); //접속 시도!!
			//대화용 클라이언트 측 쓰레드 새엇ㅇ 
			clientThread = new ClientThread(socket, this);
			clientThread.start();//청취 시작
				
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stopClient() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new ChatClient();
	}

}
