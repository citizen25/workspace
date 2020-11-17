package day201105;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class EmpApp extends JFrame{

	JButton bt_connect, bt_load;
	JTextArea area;
	JScrollPane scroll;

	String url = "jdbc:mysql://localhost/db1105";
	String user = "root";
	String password = "1234";

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public EmpApp(){
		bt_connect = new JButton("Connect");
		bt_load = new JButton("Load");
		area = new JTextArea();
		scroll = new JScrollPane(area);

		area.setPreferredSize(new Dimension(780, 500));

		setLayout(new FlowLayout());

		add(bt_connect);
		add(bt_load);
		add(scroll);

		bt_connect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				connect();
			}
		});
		bt_load.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				load();
			}
		});

		setSize(800, 600);
		setVisible(true);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				if(rs != null){
					try{
						rs.close();
					}catch(SQLException e1){
						e1.printStackTrace();
					}
				}
				if(pstmt != null){
					try{
						pstmt.close();
					}catch(SQLException e1){
						e1.printStackTrace();
					}
				}
				if(con != null){
					try{
						con.close();
					}catch(SQLException e1){
						e1.printStackTrace();
					}
				}
				System.exit(0);
			}
		});
	}

	public void connect(){
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver load success");

			con = DriverManager.getConnection(url, user, password);
			if(con == null){
				System.out.println("Connection fail");
			}else{
				System.out.println("Connection success");
			}
		}catch(ClassNotFoundException e){
			System.out.println("Driver load success");
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public void load(){
		String sql = "select * from emp";

		try{
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			area.append("EMPNO\tENAME\tJOB\tMGR\tHIREDATE\t\tSAL\tCOMM\tDEPTNO\n");

			int empno;
			String ename;
			String job;
			int mgr;
			String hiredate;
			int sal;
			int comm;
			int deptno;

			while(rs.next()){
				empno = rs.getInt("empno");
				ename = rs.getString("ename");
				job = rs.getString("job");
				mgr = rs.getInt("mgr");
				hiredate = rs.getString("hiredate");
				sal = rs.getInt("sal");
				comm = rs.getInt("comm");
				deptno = rs.getInt("deptno");

				area.append(empno+"\t"+ename+"\t"+job+"\t"+mgr+"\t"+hiredate+"\t"+sal+"\t"+comm+"\t"+deptno+"\n");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		new EmpApp();
	}

}







































