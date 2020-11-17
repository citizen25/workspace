/*DBeaver ������ �ƴϾ, ��ųʸ��� �н��ϱ� ���� �����ͺ��̽� ���� Ŭ���̾�Ʈ�� �ڹٷ� ������
	�ǹ������� SQLPlus�� �� ������� �ʴ´�. ���� ȿ������ �������� ������
		�ǹ� ���忡���� �������� pc���� ���� ���������� ������, ���� � ��������
		���Ȼ� �ƹ��͵� ��ġ�ؼ��� �ȵȴ�. ���� �������� ������ ���� ������
		���� ����Ŭ�� ���������Ϸ� �İ��� ���� ���, �ܼ�â ������� ������ �ٷ���� ��찡 ���� �ִ�
		�� �� SQLPlus�� ����ؾ� ��
	�����ڵ��� ������ �� �����ͺ��̽� �ٷ�� ���� "�����ͺ��̽� ���� Ŭ���̾�Ʈ"��� �θ���
		�̷��� ���� ��, ������ ��ǰ�� Toad ���� �ִ�(����)
		Toad�� DBeaver�� ���� ����� ����������, �����̱� ������ �츮�� DBeaver�� ���*/

package day1116.dbclient;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DBMSClientApp  extends JFrame {
	JPanel p_west;  //���� ���� �г�
	Choice ch_users;  //�������� ��µ� ���̽� ������Ʈ
	JPasswordField t_pass;  //��й�ȣ �ؽ�Ʈ�ʵ�
	JButton bt_login;  //���� ��ư
	
	JPanel p_center;  //�׸��尡 ����� ���� �г�
	JPanel p_upper;  //���̺�� �������� ������ �г�(�׸��� ���̾ƿ�)
	JTable t_tables;  //������ ���̺� ������ ����� JTable
	JTable t_seq;  //������ ������ ������ ����� JTable
	JScrollPane s1, s2;  //��ũ�� 2�� �غ�
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "system";
	String password = "1234";
	Connection con;
	
	//���̺��� ����� ���� �� �÷�
	Vector tableList = new Vector();  //������ �迭�� ����. �׷��� ������ �迭���� �����ϴ�
	Vector<String> tableColumn = new Vector<String>();  //�÷����� String
	
	//�������� �ʿ��� ���͵�
	Vector seqList = new Vector();
	Vector<String> seqColumn = new Vector<String>();
	
	//������ ���̺� ���� ���ڵ� ��¿� �ʿ��� ���͵�
	Vector recordList = new Vector();  //���ڵ带 ��� �� ����
	Vector productColumn = new Vector();  //product���̺� ���� ����
	
	
	public DBMSClientApp(){
		//����
		p_west = new JPanel();
		ch_users = new Choice();
		t_pass = new JPasswordField();
		bt_login = new JButton("����");
		
		p_center = new JPanel();
		p_upper = new JPanel();
		p_center.setLayout(new GridLayout(3, 1));  //3���� 1ȣ��
		p_upper.setLayout(new GridLayout(1, 2));  //1���� 2ȣ��
				
		//�÷� ���� �ʱ�ȭ
		tableColumn.add("table_name");
		tableColumn.add("tablespace_name");
		t_tables = new JTable(tableList, tableColumn);  //�ʱ⺤�Ͱ��� �־��ش�.
			//�� ������ ���� db������ �� �� �����̹Ƿ� ����� 0������, ���� �޼��� ȣ�� �� ������ ũ�Ⱑ ����� ���̰�
			//JTable�� ���ΰ�ħ�ϸ� �ȴ�.
		
		seqColumn.add("sequence_name");
		seqColumn.add("last_number");
		t_seq = new JTable(seqList, seqColumn);  //�������� �����ڿ� ���� ����
		
		s1 = new JScrollPane(t_tables);
		s2 = new JScrollPane(t_seq);
		
		//��Ÿ��
		p_west.setPreferredSize(new Dimension(150, 350));
		ch_users.setPreferredSize(new Dimension(145, 30));
		t_pass.setPreferredSize(new Dimension(145, 30));
		bt_login.setPreferredSize(new Dimension(145, 30));
		
		
		//����
		p_west.add(ch_users);
		p_west.add(t_pass);
		p_west.add(bt_login);
		p_upper.add(s1);
		p_upper.add(s2);
		p_center.add(p_upper);
		
		add(p_west, BorderLayout.WEST);
		add(p_center);
		
		setSize(700, 750);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
			//���μ����� ������ѹ����Ƿ�, ����Ŭ, ��Ʈ�� �ݴ� ó���� �� ��ȸ�� �Ҿ������ �ȴ�
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				disConnect();  //�ý��� ���� ���� ���� �ڿ��� ���� �ݱ�
				System.exit(0);
			}
		});
		
		bt_login.addActionListener((e)->{
			login();  //������ ������ �α��� �õ��ϱ�
		});
		
		//���̺�� ������ ����
		t_tables.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				//������ ������ ���̺�� ���
				
				int row = t_tables.getSelectedRow();  //������ row ���ϱ�
				int col = t_tables.getSelectedColumn();  //������ column ���ϱ�
				
				System.out.println(t_tables.getValueAt(row, col));
			}
		});
		
		
		setLocationRelativeTo(null);
		
		connect();
		getUserList();  //������� ���ؿ���
		
	}

	//����Ŭ�� �����ϱ�
	public void connect() {
		try {
			Class.forName(driver);  //����̹� �ε�
			con = DriverManager.getConnection(url, user, password);  //���� �õ�
			if(con == null) {
				JOptionPane.showMessageDialog(this, user+" ������ ���ӿ� �����Ͽ����ϴ�.");
			}else {
				this.setTitle(user + " �������� ���� ��..");  //������ ���� ���� ���
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//������� ��������
	public void getUserList() {
		//pstmt�� result�� �Ҹ�ǰ�̹Ƿ� �� ���������� 1���� �����ȴ�
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT username FROM dba_users ORDER BY username ASC";
		
		try {
			pstmt = con.prepareStatement(sql);  //������ �غ��ϱ�
			rs = pstmt.executeQuery();
			
			//rs�� ����ִ� ���� ������ Choice�� ���
			while(rs.next()) {
				ch_users.add(rs.getString("username"));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//���� ���� ������ ���̺� ��� ��������
	public void getTableList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT table_name, tablespace_name FROM user_tables";
		try {
			pstmt = con.prepareStatement(sql);  //���� �غ�
			rs = pstmt.executeQuery();
			//��� ��������, 2���� �迭 ���� ��, last(), getRow(), ��ũ�� �ɼ� ���
			//�����������, ���͸� �̿��ϸ� �׷��� �ʿ����
			while(rs.next()) {
				Vector vec = new Vector();  //tableList ���Ϳ� ����� ����
				vec.add(rs.getString("table_name"));
				vec.add(rs.getString("tablespace_name"));
				
				tableList.add(vec);  //������� ���Ϳ� ���͸� �������, ���� ������� ���ʹ� 2���� ���Ͱ� ��
			}
			//�ϼ��� 2���� ��Ʈ�� JTable�� �ݿ�. �������� �μ��� �ִ´�
			//�÷������� �������� ��? 2���ۿ� ������ �����ϸ� �ȴ�
			t_tables.updateUI();  //���⼭ new �ϴ°��� ��ư ���������� �ǹǷ� ���� �ʴ�. ������ new	
			
			//���̺��� ���ڵ�� �÷� ���� Ȯ�� (������ 0���� üũ)
			//���� ���̺��� ��� �νĵǰ� �ִ��� ����
			System.out.println("�÷���: " + t_tables.getColumnCount());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//������ ��������
	public void getSeqList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT sequence_name, last_number FROM user_sequences";
		
		try {
			pstmt = con.prepareStatement(sql);  //���� �غ�
			rs = pstmt.executeQuery();  //���� ����
			
			while(rs.next()) {
				Vector vec = new Vector();  //���ڵ带 ���� ���� �غ� (1����)
				vec.add(rs.getString("sequence_name"));
				vec.add(rs.getString("last_number"));
				
				//���� ������ ���Ϳ� �߰��ؼ� ������ ���ͷ� ������
				seqList.add(vec);
			}
			
			t_seq.updateUI();  //������� ���͸� ���̺� �ݿ��� ����� ����
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	//�α���
	public void login() {
		//���� �����ǰ� �ִ� ���� ��ü�� Connection�� ���� �ٸ� ������ ������ �õ��Ѵ�
		disConnect();
		user = ch_users.getSelectedItem();  //���� ���̽� ������Ʈ�� ���õ� �����۰�
		password = new String(t_pass.getPassword());  //��й�ȣ ����
		connect();  //�����ϱ�(������ ��������� ���� system���� �Ǿ������Ƿ� ��������� ���̽������� ��ü)
		getTableList();  //�� ������ �α��� ���ڸ���, �� ����� ���̺� ������ �����ش�
		getSeqList();
		System.out.println("������ ���̺� �� : " + tableList.size());
	}
	
	//����Ŭ ���� ����
	public void disConnect() {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new DBMSClientApp();
	}

}
