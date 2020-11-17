package day1030.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatC extends JFrame implements KeyListener, ActionListener{
						/* is a        is a */
	JTextArea area;
	JScrollPane scroll;
	JPanel p_south;
	JTextField t_input;
	JButton bt;
	JButton bt_open;  //��ȭ ������ ���� ��ư.
	private ChatA chatA;
	private ChatB chatB;
	
	public ChatC() {
		// ������ �θ� ���� �¾�� ��. super(), JFrame("�θ�â")
		super("�� �ڽ�â");
		
		// ����.
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_south = new JPanel();
		t_input = new JTextField(10);
		bt = new JButton("send");
		
		// �г� ���� (�г��� ����Ʈ�� FlowLayout).
		p_south.add(t_input);
		p_south.add(bt);
		
		add(scroll);  //���Ϳ� ��ũ�� ����.
		add(p_south, BorderLayout.SOUTH);  //���ʿ� �г� ����.
		
		// ��Ÿ�� ����.
		area.setBackground(Color.YELLOW);
		t_input.setBackground(Color.BLUE);
		t_input.setForeground(Color.WHITE);
		bt.setBackground(Color.BLACK);
		bt.setForeground(Color.WHITE);
		
		t_input.setPreferredSize(new Dimension(250, 30));
		
		// �����ֱ� ���� �̸� �����س���. (������Ʈ�� ������ ����)
		t_input.addKeyListener(this);
		
		// send ��ư�� ������ ����
		bt.addActionListener(this);  //���� �������� �� �������̱⵵ �ϴ�.
		
		//setSize(300, 400);
		setBounds(500, 550, 300, 400);
		setVisible(true);  //�������� �� �����ʴ�.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	@Override  //������̼� : java5���� �����Ǵ� ������ �ּ�.
	// �Ϲ����� �ּ��� ���α׷��� �������� ������, ������̼� ���α׷��ֿ��� � ǥ�ø� �ϱ� �����̹Ƿ�, ���� ����.
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// ����Ű�� ������, area�� �Է� �����͸� �ݿ�����. (����)
		int key = e.getKeyCode();  //Ű�ڵ尪 ��ȯ.
		
		if(key == 10) {			
			send();
		}

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		JButton btn = (JButton)obj;  //���� �ڷ������� ��ȯ. down casting.
		
		// ���ǹ�.
		if(btn == bt) {  //���� ��ư�� send ��ư�� �ּҰ��� ���ٸ�..
			System.out.println("send ��ư Ŭ��.");	
			send();
		}	
	}
	// �޼��� â�� ��ȭ ���� �����ϱ�.
	public void send() {
		String msg = t_input.getText();  //�ؽ�Ʈ �ʵ� ���� ���ؿ���.
		area.append(msg + "\n");
		t_input.setText("");  //�� �ؽ�Ʈ�� �ʱ�ȭ.
		
		// �ʿ� ���� ä�� ó��.
		chatA.area.append(msg + "\n");
		chatB.area.append(msg + "\n");
	}
	
	public void setChatA(ChatA chatA) {
		this.chatA = chatA;
	}
	
	public void setChatB(ChatB chatB) {
		this.chatB = chatB;
	}

}

