package day1105.db;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EmpApp2 extends JFrame{

	JButton bt_connect, bt_load;
	JTextArea area;
	JScrollPane scroll;
	
	//JDBC ����� DBMS ������ ������� �߸����� �ڵ带 �ۼ��� �� �ֵ��� �����Ѵ�
	//�ش� DB�������� �˸��� Driver�� ����ؾ� �Ѵ�
	String url = "jdbc:mysql://localhost/db1105";
	String user = "root";
	String password = "1234";
	Connection con;  //���� ��, �� ������ ���� ��ü. ����, ������ �������� �� ��ü�� �ʿ��ϴ�
	PreparedStatement pstmt;  //������ ���� ��ü. ���߿� �ݱ� ���ؼ� ��ü�� �޾ƿ�.
	//PreparedStatement�� �������̽������� new�� �����ϴ� ���� �ƴ϶� ���Ӱ�ü�� Connection��ü�κ��� �ν��Ͻ��� ���� �� �ִ�.
	//why? ������ �����Ǿ�� �������� ������ �� �����Ƿ�, ���Ӱ�ü�� �������� ���� �翬�ϴ�.
	ResultSet rs;  //select������ �������� ���� ǥ�� ��ȯ�Ǵµ�, �̶� �� ǥ�� ��� ��ü.
	
	public EmpApp2() {
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
		//setDefaultCloseOperation(EXIT_ON_CLOSE);  //DB�� ���� �ʰ�, ���μ����� �����ع����ϱ� ����ϸ� �ȵȴ�
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//�����ִ� �����ͺ��̽� ���� ��ü���� ��� ����
				//ResultSet -> PreparedStatement -> Connection ������(�������� ����)
				if(rs != null) {
					try {
						rs.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				if(pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				if(con != null) {
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				System.exit(0);  //���μ��� ����
			}
		});
	}
	
	public void connect() {
		//����Ŭ ���� �õ��ϱ� (1.����̹� �ε� 2.����)
		try {
			//����̹� �ε�
			area.append("����̹� �ε� ����\n");
			Class.forName("com.mysql.cj.jdbc.Driver");
			
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
			
			//rs���� ǥ�� ����ִ�. ���� ���ϴ� ���ڵ�� Ŀ���� �ű���
//			rs.next();  //�����Ͱ� �����ϸ�, ��ĭ ������ true ��ȯ
			
			area.append("EMPNO\tENAME\tJOB\tMGR\tHIREDATE\t\tSAL\tCOMM\tDEPTNO\n");
				
			//rs.next()�� true�� ���� �ݺ��� ����
			while(rs.next()) {
				String ename = rs.getString("ename");
				int empno = rs.getInt("empno");
				String job = rs.getString("job");
				int mgr = rs.getInt("mgr");
				String hiredate = rs.getString("hiredate");
				int sal = rs.getInt("sal");
				int comm = rs.getInt("comm");
				int deptno = rs.getInt("deptno");
				
				area.append(empno+"\t"+ename+"\t"+job+"\t"+mgr+"\t"+hiredate+"\t"+sal+"\t"+comm+"\t"+deptno+"\n");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EmpApp2();
	}
	
}
