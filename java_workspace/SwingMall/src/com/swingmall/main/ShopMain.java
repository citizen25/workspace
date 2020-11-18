package com.swingmall.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.swingmall.util.db.DBManager;

public class ShopMain extends JFrame{
	//���
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 900;
	
	JPanel usetype_container;  //������, ����� ȭ���� ���� ���� �� �ִ� �����̳�
	JPanel p_navi;  //�� ����Ʈ�� �� �޴��� ������ �����̳� �г�
	JButton[] navi = new JButton[5];  //[][][][][] �迭 ����
	String[] navi_title = {"Home", "Product", "QnA", "MyPage", "Login"}; 
	
	JLabel la_footer;  //������ �ϴ��� copyright����
	DBManager dbManager;
	Connection con;
	
	
	public ShopMain() {
		dbManager= new DBManager();
		usetype_container = new JPanel();
		p_navi = new JPanel();
		la_footer = new JLabel("SwingMall All reights reserved", SwingConstants.CENTER);
		
		con = dbManager.connect();
		if(con == null) {
			JOptionPane.showMessageDialog(this, "�����ͺ��̽��� ������ �� �����ϴ�.");
			System.exit(0);
		}else {
			this.setTitle("SwingShop�� ���� ���� ȯ���մϴ�.");
		}
	
		//��Ÿ��
		usetype_container.setPreferredSize(new Dimension(WIDTH, HEIGHT-50));
		usetype_container.setBackground(Color.WHITE);
		la_footer.setPreferredSize(new Dimension(WIDTH, 50));
		la_footer.setFont(new Font("Arial Black", Font.BOLD, 19));
		
		//����
		add(usetype_container);
		add(la_footer, BorderLayout.SOUTH);
		
		setSize(1200, 900);
		setVisible(true);
		setLocationRelativeTo(null);
		
		//�����Ӱ� ������ ����
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dbManager.disConnect(con);
			}
		});
	}
	
	
	public static void main(String[] args) {
		new ShopMain();
	}

}
