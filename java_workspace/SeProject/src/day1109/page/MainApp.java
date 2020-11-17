/*응용 어플리케이션에서 페이지 전환 방법을 학습해보자*/

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
	JPanel p_center;  //여기에 페이지들의 위치함
	
	JButton[] btn = new JButton[4];
	String[] title = {"Home", "Board", "Login", "Member"};
	Color[] colorArray = {Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN};
	
	/*4개의 준비된 페이지*/
	Home home;
	Board board;
	Login login;
	Member member;
	
	//패널들을 반복문으로 처리하려면, 개성있는 이름이 아니라, 순번으로 부르기 위한 배열이 편하다
	JPanel[] pages = new JPanel[4];
	
	public MainApp() {
		p_north = new JPanel();
		p_center = new JPanel();

		for(int i=0; i<btn.length; i++) {
			btn[i] = new JButton(title[i]);
			p_north.add(btn[i]);  //북쪽 패널에 버튼 부착
			btn[i].setBackground(colorArray[i]);
			
			btn[i].addActionListener(this);  //버튼과 연결
		}

		add(p_north, BorderLayout.NORTH);
		add(p_center, BorderLayout.CENTER);  //페이지 컨테이너를 센터에 부착
	
		//페이지 생성
		home = new Home();
		board = new Board();
		login = new Login();
		member = new Member();
		pages[0] = home; 
		pages[1] = board;
		pages[2] = login;
		pages[3] = member;
		
		//페이지 스타일 설정
		home.setPreferredSize(new Dimension(580, 480));
		board.setPreferredSize(new Dimension(580, 480));		
		login.setPreferredSize(new Dimension(580, 480));		
		member.setPreferredSize(new Dimension(580, 480));
		
		//센터영역에 4개의 준비된 페이지를 붙여넣자
		p_center.add(home);
		p_center.add(board);
		p_center.add(login);
		p_center.add(member);
		
		setVisible(true);
		pack();  //내용물의 크기만큼 수축
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();  //이벤트를 일으킨 소스 구하기
		
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
	
	//모든 페이지를 대상으로 반복문을 실행하되, 보여질 페이지만 true로, 나머지는 false로
	public void setPage(int index) {
		for(int i=0; i<pages.length; i++){
			if(index == i) {
				pages[i].setVisible(true);  //보여질 페이지							
			}else {
				pages[i].setVisible(false);  //가려질 페이지
			}
		}
		
	}
	
	public static void main(String[] args) {
		new MainApp();
	}
}
