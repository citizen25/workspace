package com.swingmall.member;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.swingmall.main.Page;
import com.swingmall.main.ShopMain;

public class Login extends Page{
	JPanel p_content;
	JTextField t_id;
	JPasswordField t_pass;
	JButton bt_login;
	JButton bt_regist;
	
	public Login(ShopMain shopMain) {
		super(shopMain);
		p_content = new JPanel();
		
		t_id = new JTextField();
		t_pass = new JPasswordField();
		bt_login = new JButton("Login");
		bt_regist = new JButton("Regist");
		
		//스타일
		p_content.setPreferredSize(new Dimension(300, 125));
		p_content.setBackground(Color.WHITE);
		t_id.setPreferredSize(new Dimension(280, 25));
		t_pass.setPreferredSize(new Dimension(280, 25));
		
		//조립
		p_content.add(t_id);
		p_content.add(t_pass);
		p_content.add(bt_login);
		p_content.add(bt_regist);
		
		add(p_content);
		
		//회원가입 버튼과 리스너 연결
		bt_regist.addActionListener((e)->{
			getShopMain().showPage(ShopMain.MEMBER_REGIST);
		});
	}

}
