package com.koreait.restproject.android;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
		setSize(300, 400);
		setVisible(true);
	}
	
	public void stopClient() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new ChatClient();
	}

}
