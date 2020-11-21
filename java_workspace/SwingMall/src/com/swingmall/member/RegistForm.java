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
		
		//��Ÿ��
		p_content.setPreferredSize(new Dimension(400, 350));
		p_content.setBackground(Color.WHITE);
		Dimension d = new Dimension(380, 25);
		t_mid.setPreferredSize(d);
		t_pass.setPreferredSize(d);
		t_name.setPreferredSize(d);
		t_phone.setPreferredSize(d);
		t_email.setPreferredSize(d);
		
		//����
		p_content.add(t_mid);
		p_content.add(t_pass);
		p_content.add(t_name);
		p_content.add(t_phone);
		p_content.add(t_email);
		p_content.add(bt_regist);
		
		add(p_content);
		
		//ȸ������ ��ư�� ������ ����
		bt_regist.addActionListener((e)->{
			if(checkId(t_mid.getText())) {  //�ߺ� ��ȸ
				JOptionPane.showInternalMessageDialog(RegistForm.this, "�ߺ��� ���̵� �Դϴ�.\n�ٸ� ���̵� ����ϼ���");
			}else {
				//�޼��� ȣ���ϱ� ����, VO ���� ä���� �Ѵ�
				ShopMember vo = new ShopMember();
				vo.setMid(t_mid.getText());  //���̵� �ֱ�
				vo.setPass(new String(t_pass.getPassword()));  //��� �ֱ�
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
	
	//ȸ�� ���� ���� üũ
	//��ȯ���� true�� ������, ȸ������ �����Ű�� �ȵ�
	public boolean checkId(String mid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		
		String sql = "select * from shop_member where mid=?";
		
		try {
			pstmt = getShopMain().getCon().prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			flag = rs.next();  //���ڵ尡 �����ϸ� true, �ƴϸ� false����
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getShopMain().getDbManager().close(pstmt, rs);
		}
		return flag;
	}
	
	//ȸ�� ���
	public int regist(ShopMember shopMember) {
		//������� �ڵ� �ʱ�ȭ�ʹ� �޸� �޼����� ���� ������ �����Ϸ��� �ʱ�ȭ���� �����Ƿ�, �ݵ�� �����ڰ� �ʱ�ȭ�ؾ� �Ѵ�
		PreparedStatement pstmt = null;
		int result = 0;  
		String sql = "insert into shop_member(member_id, mid, pass, name, phone, email)";
		sql += " values(seq_shop_member.nextval, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = getShopMain().getCon().prepareStatement(sql);
			//���ε� ���� ����
			pstmt.setString(1, shopMember.getMid());
			pstmt.setString(2, shopMember.getPass());
			pstmt.setString(3, shopMember.getName());
			pstmt.setString(4, shopMember.getPhone());
			pstmt.setString(5, shopMember.getEmail());
		
			result = pstmt.executeUpdate();  //���� ����
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getShopMain().getDbManager().close(pstmt);
		}
		return result;
	}
}
