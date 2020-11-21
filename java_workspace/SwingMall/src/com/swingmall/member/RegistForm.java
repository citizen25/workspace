package com.swingmall.member;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.swingmall.main.Page;
import com.swingmall.main.ShopMain;

public class RegistForm extends Page{
	JPanel p_content;
	JTextField t_mid;
	JPasswordField t_pass;
	JTextField t_name;
	JTextField t_phone;
	JTextField t_email;
	JButton bt_regist;
	
	public RegistForm(ShopMain shopMain) {
		super(shopMain);
		p_content = new JPanel();
		
		t_mid = new JTextField("Input ID");
		t_pass = new JPasswordField();
		t_name = new JTextField("Input Name");
		t_phone = new JTextField("Input Phone number");
		t_email = new JTextField("Input Email");
		
		bt_regist = new JButton("Regist");
		
		//스타일
		p_content.setPreferredSize(new Dimension(400, 350));
		p_content.setBackground(Color.WHITE);
		Dimension d = new Dimension(380, 25);
		t_mid.setPreferredSize(d);
		t_pass.setPreferredSize(d);
		t_name.setPreferredSize(d);
		t_phone.setPreferredSize(d);
		t_email.setPreferredSize(d);
		
		//조립
		p_content.add(t_mid);
		p_content.add(t_pass);
		p_content.add(t_name);
		p_content.add(t_phone);
		p_content.add(t_email);
		p_content.add(bt_regist);
		
		add(p_content);
		
		//회원가입 버튼과 리스너 연결
		bt_regist.addActionListener((e)->{
			if(checkId(t_mid.getText())) {  //중복 조회
				JOptionPane.showInternalMessageDialog(RegistForm.this, "중복된 아이디 입니다.\n다른 아이디를 사용하세요");
			}else {
				//메서드 호출하기 전에, VO 값을 채워야 한다
				ShopMember vo = new ShopMember();
				vo.setMid(t_mid.getText());  //아이디 넣기
				vo.setPass(new String(t_pass.getPassword()));  //비번 넣기
				vo.setName(t_name.getText());
				vo.setPhone(t_phone.getText());
				vo.setEmail(t_email.getText());
				
				int result = regist(vo);
				
				if(result == 0) {
					JOptionPane.showMessageDialog(RegistForm.this, "failed regist");
				}else {
					JOptionPane.showMessageDialog(RegistForm.this, "success regist");					
				}
				
				regist(vo);				
			}
		});
		
	}
	
	//회원 존재 여부 체크
	//반환값이 true가 나오면, 회원가입 진행시키면 안됨
	public boolean checkId(String mid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		
		String sql = "select * from shop_member where mid=?";
		
		try {
			pstmt = getShopMain().getCon().prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			flag = rs.next();  //레코드가 존재하면 true, 아니면 false대입
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getShopMain().getDbManager().close(pstmt, rs);
		}
		return flag;
	}
	
	//회원 등록
	public int regist(ShopMember shopMember) {
		//멤버변수 자동 초기화와는 달리 메서드의 지역 변수는 컴파일러가 초기화하지 않으므로, 반드시 개발자가 초기화해야 한다
		PreparedStatement pstmt = null;
		int result = 0;  
		String sql = "insert into shop_member(member_id, mid, pass, name, phone, email)";
		sql += " values(seq_shop_member.nextval, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = getShopMain().getCon().prepareStatement(sql);
			//바인드 변수 대입
			pstmt.setString(1, shopMember.getMid());
			pstmt.setString(2, shopMember.getPass());
			pstmt.setString(3, shopMember.getName());
			pstmt.setString(4, shopMember.getPhone());
			pstmt.setString(5, shopMember.getEmail());
		
			result = pstmt.executeUpdate();  //쿼리 수행
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getShopMain().getDbManager().close(pstmt);
		}
		return result;
	}
}
