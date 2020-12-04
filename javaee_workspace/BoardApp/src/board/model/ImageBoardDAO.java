/*ImageBoard ���̺� ���� CRUD���� �����ϴ� DAO ����*/

package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import board.db.DBManager;

public class ImageBoardDAO {
	DBManager dbManager = new DBManager();  //ImagesBoardDAO�� �ν��Ͻ��� ������ ��
		//DBManager�� �ν��Ͻ��� ���� ������
	
	//Create(insert)
	public int insert(ImageBoard board) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into imageboard(author, title, content, filename) values(?, ?, ?, ?)";

		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getAuthor());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getFilename());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
	
	//Read(selectAll)
	public void selectAll() {
		String sql = "select * from imageboard";
	}
	
	//Read(select)
	public void select() {
		String sql = "select * from imageboard where board_id=?";
	}
	
	//Update
	public void update() {
		String sql = "update imageboard set author=?, title=?, content=? where board_id=?";
	}
	
	//Delete
	public void delete() {
		String sql = "delete from imageboard where board_id=?";
	}
}
