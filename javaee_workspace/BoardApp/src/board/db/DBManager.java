//�����ͺ��̽� ���� �� ������ ���õ� �ڵ尡 �ʹ��� �ߺ��ǹǷ�,
//������ �������� �и����� �����ϱ� ����
package board.db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager{
	String driver = "org.mariadb.jdbc.Driver";
	String url = "jdbc:mariadb://localhost:3306/db1202";
	String user = "root";
	String password = "1234";

	//���� ��ü ���
	public Connection getConnection(){
		Connection con = null;  //return��Ű�� ����
		try{
			Class.forName(driver);  //����̹� �ε�
			System.out.println("����̹��� �ε��Ͽ����ϴ�");
			//���� �õ�
			con = DriverManager.getConnection(url, user, password);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("����̹��� �ε��� �� �����ϴ�");
		}catch(SQLException e){
			e.printStackTrace();
		}

		return con;
	}

	//�ڿ� ����
	public void release(Connection con){
		if(con != null){
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	public void release(Connection con, PreparedStatement pstmt){  //DML��
		if(pstmt != null){
			try{
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(con != null){
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	public void release(Connection con, PreparedStatement pstmt, ResultSet rs){  //Select��
		if(rs != null){
			try{
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(pstmt != null){
			try{
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(con != null){
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}