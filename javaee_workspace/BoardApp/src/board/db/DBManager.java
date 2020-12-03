//데이터베이스 접속 및 해제와 관련된 코드가 너무나 중복되므로,
//공통의 로직으로 분리시켜 재사용하기 위함
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

	//접속 객체 얻기
	public Connection getConnection(){
		Connection con = null;  //return시키기 위해
		try{
			Class.forName(driver);  //드라이버 로드
			System.out.println("드라이버를 로드하였습니다");
			//접속 시도
			con = DriverManager.getConnection(url, user, password);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("드라이버를 로드할 수 없습니다");
		}catch(SQLException e){
			e.printStackTrace();
		}

		return con;
	}

	//자원 해제
	public void release(Connection con){
		if(con != null){
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	public void release(Connection con, PreparedStatement pstmt){  //DML용
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
	public void release(Connection con, PreparedStatement pstmt, ResultSet rs){  //Select용
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