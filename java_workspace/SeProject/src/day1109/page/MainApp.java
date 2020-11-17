/*���� ���ø����̼ǿ��� ������ ��ȯ ����� �н��غ���*/

package day1109.page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainApp extends JFrame implements ActionListener{
	JPanel p_north;
	JPanel p_center;  //���⿡ ���������� ��ġ��
	
	JButton[] btn = new JButton[4];
	String[] title = {"Home", "Board", "Login", "Member"};
	Color[] colorArray = {Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN};
	
	/*4���� �غ�� ������*/
	Home home;
	Board board;
	Login login;
	Member member;
	
	//�гε��� �ݺ������� ó���Ϸ���, �����ִ� �̸��� �ƴ϶�, �������� �θ��� ���� �迭�� ���ϴ�
	JPanel[] pages = new JPanel[4];
	
	public MainApp() {
		p_north = new JPanel();
		p_center = new JPanel();

		for(int i=0; i<btn.length; i++) {
			btn[i] = new JButton(title[i]);
			p_north.add(btn[i]);  //���� �гο� ��ư ����
			btn[i].setBackground(colorArray[i]);
			
			btn[i].addActionListener(this);  //��ư�� ����
		}

		add(p_north, BorderLayout.NORTH);
		add(p_center, BorderLayout.CENTER);  //������ �����̳ʸ� ���Ϳ� ����
	
		//������ ����
		home = new Home();
		board = new Board();
		login = new Login();
		member = new Member();
		pages[0] = home; 
		pages[1] = board;
		pages[2] = login;
		pages[3] = member;
		
		//������ ��Ÿ�� ����
		home.setPreferredSize(new Dimension(580, 480));
		board.setPreferredSize(new Dimension(580, 480));		
		login.setPreferredSize(new Dimension(580, 480));		
		member.setPreferredSize(new Dimension(580, 480));
		
		//���Ϳ����� 4���� �غ�� �������� �ٿ�����
		p_center.add(home);
		p_center.add(board);
		p_center.add(login);
		p_center.add(member);
		
		setVisible(true);
		pack();  //���빰�� ũ�⸸ŭ ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();  //�̺�Ʈ�� ����Ų �ҽ� ���ϱ�
		
		if(obj == btn[0]) {  //home
			setPage(0);
		}else if(obj == btn[1]) {  //board
			setPage(1);
		}else if(obj == btn[2]) {  //login
			setPage(2);
		}else if(obj == btn[3]) {  //member
			setPage(3);
		}
	}
	
	//��� �������� ������� �ݺ����� �����ϵ�, ������ �������� true��, �������� false��
	public void setPage(int index) {
		for(int i=0; i<pages.length; i++){
			if(index == i) {
				pages[i].setVisible(true);  //������ ������							
			}else {
				pages[i].setVisible(false);  //������ ������
			}
		}
		
	}
	
	public static void main(String[] args) {
		new MainApp();
	}
}
