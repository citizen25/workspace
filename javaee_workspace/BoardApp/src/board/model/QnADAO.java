package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.db.DBManager;

public class QnADAO {
	
	DBManager dbManager = new DBManager();
	
	//insert : 원 글 등록
	public int insert(QnA qna) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into qna(writer, title, content) values(?, ?, ?)";
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna.getWriter());
			pstmt.setString(2, qna.getTitle());
			pstmt.setString(3, qna.getContent());
			result = pstmt.executeUpdate();  //실행
			
			//team을 방금 들어간 레코드에 의해  발생한 pk값으로 업데이트
			sql = "update qna set team=(select last_insert_id()) where qna_id=(select last_insert_id());";
			pstmt = con.prepareStatement(sql);  //pstmt는 쿼리 하나당 하나가 할당되어야 한다
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}
		
		
		return result;
	}
	
	/*
		1.기존에 내가 본 글보다 rank가 큰 글의 rank는 모두 1씩 등가되시오(공간 확보)
			update qna rank=rank+1 where rank > 내본글 rank and team=내본team
			
		2.빈 공간을 내가 차지!!(답변)
   			insert  qna(~team, rank, depth) values(내본team,내본rank+1,내본depth+1)
   			
   		트랜잭선이란?
   			세부업무가 모두 성공해야 전체를 성공으로 간주하는 논리적 업무수행 단위
   			-commit : 트랜잭션이 확정되면서 새로운 트랜잭선 시작 
   			-rollback : 트랜잭션이 취소된다
	 */
	
	public int replay(QnA qna) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		con = dbManager.getConnection();
		
		
		try {
			con.setAutoCommit(false);  //자동으로 커밋하지 마! (SQLPlus처럼 내가 결정한다)
			String sql = "update qna set rank=rank+1 where team=? and rank > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna.getTeam());
			pstmt.setInt(2, qna.getRank());
			result = pstmt.executeUpdate();
			
			sql = "insert into qna(writer, title, content, team, rank, depth)";		
			sql += " values(?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna.getWriter());
			pstmt.setString(2, qna.getTitle());
			pstmt.setString(3, qna.getContent());
			pstmt.setInt(4, qna.getTeam());
			pstmt.setInt(5, qna.getRank()+1);  //내본글 다음에 위치할 것이므로 +1
			pstmt.setInt(6, qna.getDepth()+1);  //내본글에 대한 답글이므로 +1
			result = pstmt.executeUpdate();

			con.commit();  //여기서 커밋. 즉, 둘 다 에러나지 않고 try를 완료하면 모두 성공으로 간주. 여기서 commit
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();  //두 쿼리문 중 에러가 하나라도 발생하면, 차라리 처음부터 없었던 일로 하자
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				con.setAutoCommit(true);  //원상 복귀
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dbManager.release(con, pstmt);
		}
		
		return result;
	}
	
	//insert : 답변글 등록
	public int reply() {
		int result = 0;
		
		return result;
	}
	
	//selectAll
	public List selectAll() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<QnA> list = new ArrayList<QnA>();
		
		String sql = "select * from qna order by team desc, rank asc";

		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				QnA qna = new QnA();  //레코드 만큼 vo생성해야 함
				qna.setQna_id(rs.getInt("qna_id"));				
				qna.setWriter(rs.getString("writer"));
				qna.setTitle(rs.getString("title"));
				qna.setContent(rs.getString("content"));
				qna.setRegdate(rs.getString("regdate"));
				qna.setHit(rs.getInt("hit"));
				qna.setTeam(rs.getInt("team"));
				qna.setRank(rs.getInt("rank"));
				qna.setDepth(rs.getInt("depth"));
				
				list.add(qna);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt, rs);
		}
		
		return list;
	}
	
	//select
	public QnA select(int qna_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QnA qna = null;
		
		String sql = "select * from qna where qna_id = ?";

		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {  //레코드가 있다면
				qna = new QnA();
				qna.setQna_id(rs.getInt("qna_id"));				
				qna.setWriter(rs.getString("writer"));
				qna.setTitle(rs.getString("title"));
				qna.setContent(rs.getString("content"));
				qna.setRegdate(rs.getString("regdate"));
				qna.setHit(rs.getInt("hit"));
				qna.setTeam(rs.getInt("team"));
				qna.setRank(rs.getInt("rank"));
				qna.setDepth(rs.getInt("depth"));		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt, rs);
		}
		
		return qna;
	}
	
	//update
	public int update() {
		int result = 0;
		
		return result;	
	}
	
	//delete
	public int delete() {
		int result = 0;
		
		return result;		
	}
	
	
}
