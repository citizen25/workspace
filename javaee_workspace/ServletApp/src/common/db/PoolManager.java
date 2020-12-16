//Connection Pool ����� ���� ���ϰ� ó���ؾ� DAO �鿡�� ��� �� �Ѵ�

package common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PoolManager {
	InitialContext context;  //JNDI �˻��� ����ϴ� ��ü
	DataSource ds;  //Connection Pool
	private static PoolManager instance;
	
	//private�̱� ������ �ƹ��� new�� �� ����
	private PoolManager() {
		try {
			context = new InitialContext();  //�˻� ��ü ����
			ds = (DataSource)context.lookup("java:comp/env/jdbc/myoracle");  //ã�� ���� & Pool return
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	//������ class�� ����� ���� ���̱� ������, instance�� ������ ��ȸ�� ���� class���� �δ�������
	public static PoolManager getInstance() {
		if(instance == null) {
			instance = new PoolManager();  //���� ������ new�� �� �ִ�
		}
		return instance;
	}
	
	//Connection�� �ʿ��� �ڿ��� Connection�� return���ִ� (�뿩) method
	public Connection getConnection() {
		Connection con = null;
		try {
			con = ds.getConnection();  //�뿩
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return con;
	}
	
	//�ݳ�(overloading)
	public void release(Connection con) {
		if(con != null) {
			try {
				con.close();  //close�ϸ� �ݳ��ǵ��� ������� �ִ�
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	public void release(Connection con, PreparedStatement pstmt) {
		if(con != null) {
			try {
				con.close();  //close�ϸ� �ݳ��ǵ��� ������� �ִ�
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			try {
				pstmt.close();  //close�ϸ� �ݳ��ǵ��� ������� �ִ�
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	public void release(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if(con != null) {
			try {
				rs.close();  //close�ϸ� �ݳ��ǵ��� ������� �ִ�
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			try {
				pstmt.close();  //close�ϸ� �ݳ��ǵ��� ������� �ִ�
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			try {
				con.close();  //close�ϸ� �ݳ��ǵ��� ������� �ִ�
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	
}
