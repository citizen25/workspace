package day1110.network.multicasting;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MultiServer extends JFrame{
	JTextField t_port;
	JButton bt;
	JPanel p_north;
	JTextArea area;
	JScrollPane scroll;
	
	ServerSocket server;
	int port=7777;
	
	Thread thread;  //���ξ����� ��� �����ڸ� �����ϰԵ� ������ (accept() �޼��� ������)
	//�ڹ��� �÷��� �����ӿ� �� �����ִ� �����͸� ó�����ִ� ��ü
	Vector<MessageThread> clientList = new Vector<MessageThread>();
	
	public MultiServer() {
		t_port = new JTextField(Integer.toString(port), 10);
		bt = new JButton("���� ����");
		p_north = new JPanel();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		//����
		p_north.add(t_port);
		p_north.add(bt);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		//���� ��ư�� ������ ����
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thread = new Thread() {
					public void run() {
						startServer();						
					}
				};
				thread.start();  //Runnable�� ���Խ�Ű��
			}
		});

		setVisible(true);
		setBounds(600, 200, 300, 400);  //x, y, width, height
	}
	
	//���� ����
	public void startServer() {
		try {
			server = new ServerSocket(Integer.parseInt(t_port.getText()));  //�������� ����
			area.append("���� �غ�\n");
			//������ �������� �����ڸ� �����ؾ��Ѵٸ�, �� �����ڸ��� �񵿱�������(����������) ������� 
			//��ȭ�� �ְ� �޴� ��ü�� �������� �ν��Ͻ��� ó������
			while(true) {
				//���� �����ϱ�
				Socket socket = server.accept();  //������ ������ ���ÿ� ��ȭ�� ���� ��ȯ
				area.append("������ �߰�\n");
				
				//�ټ��� ������ �� ������ ��򰡿� �����س���
				

				//��ȭ�� �����带 �����ϰ�, ������ �Ѱ�����
				MessageThread messageThread = new MessageThread(this, socket);
				messageThread.start();  //Runnable�� ����
				
				clientList.add(messageThread);  
				//���� ������ Ŭ���̾�Ʈ�� ���� �̷�� ������ ��ȭ �����带 ���Ϳ� ����ָ�, 
				//�� ����� ���԰�, ���� ���Դ����� ���� �����͸� ������ �� �ְ� �ȴ�
				area.append("������� �����ڴ� "+clientList.size()+"��\n");
				
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	//main thread�� ���� 1.���ѷ��� 2.��� ���¿� ���߷����� �ȵȴ� 
	public static void main(String[] args) {
		new MultiServer();
	}

}
