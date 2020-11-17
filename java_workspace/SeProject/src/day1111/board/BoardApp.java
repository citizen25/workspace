package day1111.board;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import common.image.ImageUtil;
import day1111.member.BoardMember;
import day1111.member.Login;
import day1111.member.RegistForm;

//BoardApp가 모든 페이지들을 보유하고 있는 최상이 컨테이너이므로,
//각 페이지들마다 서로 공유할 데이터가 있다면, BoardApp에 데이터를 두고 처리하면 됨

public class BoardApp extends JFrame{
	JPanel p_north;
	JButton bt_board, bt_regist, bt_login;
	JPanel p_center;

	private JPanel[] pages = new JPanel[5];  //화면전환에 사용될 패널들을 담게 될 패널

	//상수란? 변하지 않는 데이터에 의미를 부여하여 직관성을 높이기 위해 사용된다
	public static final int BOARD_LIST = 0;
	public static final int BOARD_WRITE = 1;
	public static final int BOARD_DETAIL= 2;
	public static final int MEMBER_REGIST= 3;
	public static final int MEMBER_LOGIN= 4;

	//접속에 필요한 정보들
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private String user = "user1104";
	private String pass ="user1104";

	//모든 페이지들이 사용할 접속 정보 객체를 공통으로 선언
	//이 커넥션 객체는 프로그램 가동과 동시에 접속을 얻어다 놓는 역할
	private Connection con;
	
	//로그인 상태 여부를 보관할 변수
	private boolean hasSession = false;  //세션을 보유하고 있는지
	
	//회원정보를 보관할 자료형
	BoardMember boardMember;  //이 변수에 데이터가 채워지는 시점(인스턴스 생성)은 로그인 성공할 때
	
	public BoardApp() {
		this.getConnection();  //프레임을 보여주기 직전에 데이터베이스 접속 성공해놓자

		//생성
		p_north = new JPanel();	
		bt_board = new JButton(ImageUtil.getIcon(this.getClass(), "res/icon_board.png", 90, 35));
		bt_regist = new JButton(ImageUtil.getIcon(this.getClass(), "res/icon_regist.png", 90, 35));
		bt_login = new JButton(ImageUtil.getIcon(this.getClass(), "res/icon_login.png", 90, 35));
		p_center = new JPanel();
		
		pages[0] = new BoardList(this);  //게시판 목록 페이지
		pages[1] = new BoardWrite(this);  //게시판 글쓰기 페이지
		pages[2] = new BoardDetail(this);  //게시판 상세보기 페이지
		pages[3] = new RegistForm(this);  //회원가입 페이지
		pages[4] = new Login(this);  //로그인 페이지
		
		//스타일
		bt_board.setPreferredSize(new Dimension(90, 35));
		bt_regist.setPreferredSize(new Dimension(90, 35));
		bt_login.setPreferredSize(new Dimension(90, 35));
		
		//조립
		p_north.add(bt_board);
		p_north.add(bt_regist);
		p_north.add(bt_login);
		
		p_center.add(pages[0]);  //중앙 패널에 게시판 목록 붙여넣기
		p_center.add(pages[1]);  //중앙 패널에 게시판 글쓰기 붙여넣기
		p_center.add(pages[2]);  //중앙 패널에 게시판 상세보기 붙여넣기
		p_center.add(pages[3]);  //중앙 패널에 회원가입 붙여넣기
		p_center.add(pages[4]);  //중앙 패널에 로그인 붙여넣기
		
		add(p_north, BorderLayout.NORTH);
		add(p_center);
	
		//디폴트로 보여질 페이지와 안 보이게 될 페이지에대한 처리
		loginCheck(BoardApp.BOARD_LIST);
		
		setVisible(true);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				disConnection();  //접속 해제
			}
		});
		
		//게시판 버튼과 리스너 연결
		bt_board.addActionListener((e)->{
			setPage(BOARD_LIST);
		});
		
		//가입 버튼과 리스너 연결
		//Lambda표현식 : 함수형 프로그램 표현식이다. java8부터 지원
		//참고) javaScript에서는 람다를 클로저라 한다
		//이벤트 구현시, 재정의할 메서드가 달랑 1개인 경우, 굳이 내부 익명클래스라는 표기를 거창하게 사용해야하나?
		//기능이 같지만 표기법이 간결해짐
		//람다 표현식은 객체를 마치 함수처럼 간결하게 사용할 수 있도록 지원하는 표기법이다
		bt_regist.addActionListener((e) -> {
			//System.out.println("bt_regist 클릭");
			setPage(MEMBER_REGIST);
		});
		
		//로그인 버튼과 리스너 연결
		bt_login.addActionListener((e)-> {
			setPage(MEMBER_LOGIN);
		});
	}
	
	//이 프로그램에서 사용되는 모든 페이지를 제어하는 메서드
	public void setPage(int showIndex) {  //보여주고 싶은 페이지 index 넘겨받자
		for(int i=0; i<pages.length; i++) {
			if(i == showIndex) {
				pages[i].setVisible(true);				
			}else {
				pages[i].setVisible(false);				
			}
		}
	}

	//로그인 여부를 판단해서 만일 로그인하지 않았다면, 로그인 페이지 보여주기
	public void loginCheck(int page) {
		if(hasSession == false) {  //로그인하지 않은 상태임
			JOptionPane.showMessageDialog(this, "로그인이 필요한 서비스입니다");
			setPage(BoardApp.MEMBER_LOGIN);
		}else{  //로그인한 사람에게는 원하는 페이지를 보여준다
			setPage(page);
		}
	}
	
	//접속을 시도하는 메서드 정의
	public void getConnection() {
		try {
			Class.forName(driver);  //드라이버 로드
			con = DriverManager.getConnection(url, user, pass); //접속시도 후, 객체 반환
			if(con == null) {  //접속 실패인 경유 메시지 출력
				JOptionPane.showMessageDialog(this, "데이터베이스에 접속하지 못했습니다.");
			}else {  //접속 성공의 경우, 윈도우 제목창에 현재 접속자 출력
				this.setTitle(user + " 접속 중");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//접속을 해제하는 메서드 정의
	//이 메서드는 윈도우 창을 닫을 때 호출될 예정
	public void disConnection() {
		if(con != null) {  //null이 아닐 때만 닫아야 함. 만일 이런 확인 절차를 거치지 않으면 NullPointerExcetpion 발생할 수 있음
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//다른 객체들이 접근할 수 있도록 getter 제공
	public Connection getCon() {
		return con;
	}
	
	//getter, setter
	public boolean isHasSession() {
		return hasSession;
	}

	public void setHasSession(boolean hasSession) {
		this.hasSession = hasSession;
	}
	
	public BoardMember getBoardMember() {
		return boardMember;
	}
	
	public void setBoardMember(BoardMember boardMember) {
		this.boardMember = boardMember;
	}
	
	//각 페이지를 접근할 수 있는 getter
	public JPanel getPages(int pageName) {
		return pages[pageName];
	}
	
	
	
	public static void main(String[] args) {
		new BoardApp();
	}


}
