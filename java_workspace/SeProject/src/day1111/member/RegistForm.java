package day1111.member;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import day1111.board.BoardApp;

public class RegistForm extends JPanel{
	JPanel p_container;  //묶기위한 컨테이너
	JTextField t_id;
	JPasswordField t_pass;
	JTextField t_name;
	JButton bt;
	JButton bt_login;
	BoardApp boardApp;
	Connection con;

	public RegistForm(BoardApp boardApp) {
		this.boardApp = boardApp;
		con = boardApp.getCon();  //다른 패키지의 정보에 접근하는 법 : getter
		//System.out.println("나 태어날 때 con은 " + con);
		
		p_container = new JPanel();
		t_id = new JTextField(30);
		t_pass = new JPasswordField(30);
		t_name = new JTextField(30);
		bt = new JButton("가입");
		bt_login = new JButton("로그인");
		
		p_container.setBackground(Color.YELLOW);
		p_container.setPreferredSize(new Dimension(400, 200));
		
		p_container.add(t_id);
		p_container.add(t_pass);
		p_container.add(t_name);
		p_container.add(bt);
		p_container.add(bt_login);
		
		add(p_container);
		setVisible(true);
		
		//가입버튼과 리스너 연결
		bt.addActionListener((e)->{
			regist();
		});
		
		bt_login.addActionListener((e)->{
			boardApp.setPage(BoardApp.MEMBER_LOGIN);
		});
	}
	
	//oracle에 접속하여 insert 실행
	public void regist() {
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO board_member(member_id, m_id, m_pass, m_name)";
		sql += " VALUES(seq_board_member.nextval, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);  //쿼리 실행할 준비
			pstmt.setString(1, t_id.getText());
			pstmt.setString(2, new String(t_pass.getPassword()));  //char 배열을 String으로
			pstmt.setString(3, t_name.getText());
			
			//DML의 경우, 이 쿼리 수행에 의해 영향을 받은 레코드수가 반환되므로, 만일 0이 반환된다면 실패로 판단
			int result = pstmt.executeUpdate();  //DML의 경우는 executeUpdate(), select는 executeQuery()
			if(result == 0) {
				JOptionPane.showMessageDialog(this, "가입에 실패하였습니다.\n관리자에 문의주세요.");
			}else {
				JOptionPane.showMessageDialog(this, "가입 축하드립니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
















