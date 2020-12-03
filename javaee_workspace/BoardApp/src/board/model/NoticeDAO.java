//DAO��? 
//- Data Access Object�� �ǹ��ϴ� ���ø����̼��� ���� �о� ���
//- Data Access�� database���� CRUD�۾��� �����Ѵٴ� �ǹ�

package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.db.DBManager;

public class NoticeDAO {
	DBManager dbManager = new DBManager();
	
	//insert�� �� �� �� == �ϳ��� VO
	public int regist(Notice notice) {

		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;  //�� ��� �� �� ����� ����
		
		con = dbManager.getConnection();
		
		String sql = "insert into notice(author, title, content) values(?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, notice.getAuthor());  //�ۼ���
			pstmt.setString(2, notice.getTitle());  //����
			pstmt.setString(3, notice.getContent());  //����
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}
		
		return result;
	
	}

	//��� record ��������
	public ArrayList selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Notice> list = new ArrayList<Notice>();  //rs�� ��ü�� �༮��
		
		con = dbManager.getConnection();
		String sql = "select * from notice order by notice_id desc";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//rs�� record�� �������̹Ƿ�, VO���� �������� �ʿ��ϰ�, �� VO�� �Ѳ����� ��Ƽ�
			//��ȯ�ؾ��ϹǷ�, ������ �ڷ����� �ʿ��ϴ�. ��ü�� ��Ƴ��� ������ �����ϴ� �����̿���
			//CollectionFramework�̹Ƿ�, �� �� �ϳ��� api�� �̿��Ϻ���.
			while(rs.next()) {
				Notice notice = new Notice();  //�� �� empty ������ VO ����
				//notice�� rs�� ������ ��� �Ű� ����
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setAuthor(rs.getString("author"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getNString("content"));
				notice.setRegdate(rs.getNString("regdate"));
				notice.setHit(rs.getInt("hit"));
				
				//notice ������ ������� ���� �� list�� ����
				list.add(notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt, rs);
		}
		
		return list;  //rs�� �ƴ� ArrayList�� ��ȯ
	}
	
	//�Խù� 1�� ��������(�󼼺���)
	public Notice select(int notice_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Notice notice = null;  //rs��� ������ 1���� ���� ��ü
		
		String sql = "select * from notice where notice_id=" + notice_id;
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_id);  //���ε� ������ ����
			rs = pstmt.executeQuery();
			
			//���� ź���� rs�� �� �״´�. ���� rs�� ��ü�� ��ü�� �ʿ��ϴ�
			//rs�� record �� ���� ��� ��ü�̹Ƿ�, record 1���� ��� ���޿����� ���Ǵ� VO�� �̿�����
			if(rs.next()) {  //record�� �����ϴ� ����. ���� �̶� VO�� �ø���
				notice = new Notice();  //�� �� empty ������ VO ����
				//notice�� rs�� ������ ��� �Ű� ����
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setAuthor(rs.getString("author"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getNString("content"));
				notice.setRegdate(rs.getNString("regdate"));
				notice.setHit(rs.getInt("hit"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt, rs);
		}
		
		return notice;
	}
	
}
