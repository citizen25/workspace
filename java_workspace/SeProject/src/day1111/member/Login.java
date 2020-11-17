package day1111.member;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import day1111.board.BoardApp;

public class Login extends JPanel{
	JPanel p_container;
	JTextField t_id;
	JPasswordField t_pass;
	JButton bt_regist;
	JButton bt_login;
	BoardApp boardApp;
	Connection con;
	
	public Login(BoardApp boardApp) {
		this.boardApp=boardApp;
		con = boardApp.getCon();
		
		p_container = new JPanel();
		t_id = new JTextField(30);
		t_pass = new JPasswordField(30);
		bt_regist = new JButton("ȸ������");
		bt_login = new JButton("�α���");
		
		p_container.setPreferredSize(new Dimension(400, 150));
		
		p_container.add(t_id);
		p_container.add(t_pass);
		p_container.add(bt_regist);
		p_container.add(bt_login);
		
		add(p_container);
		
		setVisible(true);
		
		bt_regist.addActionListener((e)->{
			boardApp.setPage(BoardApp.MEMBER_REGIST);
		});
		
		//�α��� ��ư�� ������ ���� 
		bt_login.addActionListener((e)->{
			login();
		});
	}
	
	/*
	 * �����ͺ��̽� ������ ��� �޼��帶�� ,����~���⸦ �ݺ��Ѵٸ� �ڵ��ߺ������� �߻��� ���̰�
	 * ȿ������ �������̹Ƿ� , ���α׷� ������ ���ÿ� �ѹ� �����س���, ���α׷� �����Ҷ��� �ѹ� ���������ϴ°� 
	 * ������ ����, ���� �� ���������� ����,���� ó���������� �ֻ��� �����̳��� JFrame���� ���� �� ����ó���� 
	 * �ϰ���.
	 * */
	public void login() {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql="select * from board_member where m_id=? and m_pass=?";
		try {
			pstmt=con.prepareStatement(sql); //������ �غ� 
			pstmt.setString(1, t_id.getText());
			pstmt.setString(2, new String(t_pass.getPassword()));
			
			rs = pstmt.executeQuery();//��������, select���̹Ƿ� ���ڵ带 ���� ResultSet�� ��ȯ�ȴ�
			//�α����� ���, ����� �� ������ �� ��� ���ڵ�� ������ ����� ���ñ�? 1��
			//rs�� ��ȯ�� �������ʹ�,rs�� Ŀ���� �������� ���ڵ尡 �����Ѵٸ�? ������������ ���� 
			//���ٸ�?? �α��� ����~!
			if(rs.next()) { //���ڵ尡 �����Ѵٸ�...ȸ������ ���� 
				JOptionPane.showMessageDialog(this, "��������");
				
				//�α����� �������� �� ȸ�������� ���� + ���� ������ true��
				boardApp.setHasSession(true);
				
				//ȸ������ ä���ֱ�
				BoardMember boardMember = new BoardMember();  //empty
				boardMember.setMember_id(rs.getInt("member_id"));  //pk
				boardMember.setM_id(rs.getString("m_id"));  //id
				boardMember.setM_pass(rs.getString("m_pass"));  //pass
				boardMember.setM_name(rs.getString("m_name"));  //name
				boardMember.setRegdate(rs.getString("regdate"));  //regdate
				
				//BoardApp�� ������ ȸ������ ��ü�� �ּ� ����
				boardApp.setBoardMember(boardMember);
				
				//���� �������� ������(�Խ��� ���)
				boardApp.setPage(BoardApp.BOARD_LIST);
			}else {
				JOptionPane.showMessageDialog(this, "�α��� ������ �ùٸ��� �ʽ��ϴ�");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
}