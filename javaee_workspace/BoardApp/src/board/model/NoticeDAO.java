//DAO란? 
//- Data Access Object를 의미하는 어플리케이션의 설계 분야 용어
//- Data Access란 database와의 CRUD작업을 전담한다는 의미

package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.db.DBManager;

public class NoticeDAO {
	DBManager dbManager = new DBManager();
	
	//insert는 글 한 건 == 하나의 VO
	public int regist(Notice notice) {

		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;  //글 등록 후 그 결과값 보관
		
		con = dbManager.getConnection();
		
		String sql = "insert into notice(author, title, content) values(?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, notice.getAuthor());  //작성자
			pstmt.setString(2, notice.getTitle());  //제목
			pstmt.setString(3, notice.getContent());  //내용
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}
		
		return result;
	
	}

	//모든 record 가져오기
	public ArrayList selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Notice> list = new ArrayList<Notice>();  //rs를 대체할 녀석들
		
		con = dbManager.getConnection();
		String sql = "select * from notice order by notice_id desc";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//rs는 record가 여러개이므로, VO또한 여러개가 필요하고, 이 VO를 한꺼번에 모아서
			//반환해야하므로, 집합형 자료형이 필요하다. 객체를 모아놓은 집합을 지원하는 프레이웍은
			//CollectionFramework이므로, 이 중 하나의 api를 이용하본다.
			while(rs.next()) {
				Notice notice = new Notice();  //텅 빈 empty 상태의 VO 생성
				//notice에 rs의 정보를 모두 옮겨 심자
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setAuthor(rs.getString("author"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getNString("content"));
				notice.setRegdate(rs.getNString("regdate"));
				notice.setHit(rs.getInt("hit"));
				
				//notice 변수가 사라지기 전에 얼른 list에 담자
				list.add(notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt, rs);
		}
		
		return list;  //rs가 아닌 ArrayList를 반환
	}
	
	//게시물 1건 가져오기(상세보기)
	public Notice select(int notice_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Notice notice = null;  //rs대신 데이터 1건을 담을 객체
		
		String sql = "select * from notice where notice_id=" + notice_id;
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_id);  //바인드 변수값 지정
			rs = pstmt.executeQuery();
			
			//지금 탄생한 rs는 곧 죽는다. 따라서 rs를 대체할 객체가 필요하다
			//rs는 record 한 건을 담는 객체이므로, record 1건을 담아 전달용으로 사용되는 VO를 이용하자
			if(rs.next()) {  //record가 존재하는 것임. 따라서 이때 VO를 올리자
				notice = new Notice();  //텅 빈 empty 상태의 VO 생성
				//notice에 rs의 정보를 모두 옮겨 심자
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
