package day1105.db;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EmpApp extends JFrame{

	JButton bt_connect, bt_load;
	JTextArea area;
	JScrollPane scroll;
	
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "user1104";
	String password = "user1104";
	Connection con;  //���� ��, �� ������ ���� ��ü. ����, ������ �������� �� ��ü�� �ʿ��ϴ�
	PreparedStatement pstmt;  //������ ���� ��ü. ���߿� �ݱ� ���ؼ� ��ü�� �޾ƿ�.
	//PreparedStatement�� �������̽������� new�� �����ϴ� ���� �ƴ϶� ���Ӱ�ü�� Connection��ü�κ��� �ν��Ͻ��� ���� �� �ִ�.
	//why? ������ �����Ǿ�� �������� ������ �� �����Ƿ�, ���Ӱ�ü�� �������� ���� �翬�ϴ�.
	ResultSet rs;  //select������ �������� ���� ǥ�� ��ȯ�Ǵµ�, �̶� �� ǥ�� ��� ��ü.
	
	public EmpApp() {
		bt_connect = new JButton("Connect");
		bt_load = new JButton("Load");
		
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		//��Ÿ�� ����
		area.setPreferredSize(new Dimension(780, 500));
		
		setLayout(new FlowLayout());
		
		add(bt_connect);
		add(bt_load);
		add(scroll);
		
		//��ư�� ������ ����
		bt_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();  //����Ŭ ����
			}
		});
		bt_load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load();  //select�� emp��������
			}
		});
		
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void connect() {
		//����Ŭ ���� �õ��ϱ� (1.����̹� �ε� 2.����)
		try {
			//����̹� �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");
			area.append("����̹� �ε� ����\n");
			
			//���� �õ�
			con = DriverManager.getConnection(url, user, password);
			if(con == null) {
				area.append("���� ����\n");
			}else {
				area.append("���� ����\n");				
			}
			
		} catch (ClassNotFoundException e) {
			area.append("����̹� �ε� ����\n");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void load() {
		//select���� �����غ���
		String sql = "select * from emp";
		
		try {
			pstmt = con.prepareStatement(sql);
			//pstmt.excuteUpdate();  //DML(insert, update delete)�� ��츸 executeUpdate()
			rs = pstmt.executeQuery(sql);  //select���� ��쿣 executeQuery() �޼��� �̿�. ResultSet�� ��ȯ��
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EmpApp();
	}
	
}
