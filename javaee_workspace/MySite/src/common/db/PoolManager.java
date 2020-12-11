//Connection Pool 사용을 보다 편하게 처리해야 DAO 들에서 고생 안 한다

package common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PoolManager {
	InitialContext context;  //JNDI 검색을 담당하는 객체
	DataSource ds;  //Connection Pool
	private static PoolManager instance;
	
	//private이기 때문에 아무도 new할 수 없다
	private PoolManager() {
		try {
			context = new InitialContext();  //검색 객체 생성
			ds = (DataSource)context.lookup("java:comp/env/jdbc/myoracle");  //찾기 성공 & Pool return
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	//하지만 class는 쓰라고 만든 것이기 때문에, instance를 가져갈 기회를 현재 class에서 부담해주자
	public static PoolManager getInstance() {
		if(instance == null) {
			instance = new PoolManager();  //오직 나만이 new할 수 있다
		}
		return instance;
	}
	
	//Connection이 필요한 자에게 Connection을 return해주는 (대여) method
	public Connection getConnection() {
		Connection con = null;
		try {
			con = ds.getConnection();  //대여
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return con;
	}
	
	//반납(overloading)
	public void release(Connection con) {
		if(con != null) {
			try {
				con.close();  //close하면 반납되도록 만들어져 있다
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	public void release(Connection con, PreparedStatement pstmt) {
		if(con != null) {
			try {
				con.close();  //close하면 반납되도록 만들어져 있다
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			try {
				pstmt.close();  //close하면 반납되도록 만들어져 있다
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	public void release(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if(con != null) {
			try {
				rs.close();  //close하면 반납되도록 만들어져 있다
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			try {
				pstmt.close();  //close하면 반납되도록 만들어져 있다
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			try {
				con.close();  //close하면 반납되도록 만들어져 있다
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	
}
