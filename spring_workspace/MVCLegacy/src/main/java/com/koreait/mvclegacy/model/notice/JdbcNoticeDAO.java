package com.koreait.mvclegacy.model.notice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.koreait.mvclegacy.exception.DMLException;
import com.koreait.mvclegacy.model.domain.Notice;

//Spring이 지원하는 DB연동 기술에는 여러가지가 있다
//[Mybatis]
//SQL Mapper : 쿼리문과 자바 객체 간 매핑

//[JDBC 자체]

//[Hibernate]
//ORM (Object Relation Mapping) : 자바 객체와 관계형 DB와의 매핑

//[JPA]

@Repository
public class JdbcNoticeDAO implements NoticeDAO {
	private static final Logger logger = LoggerFactory.getLogger(JdbcNoticeDAO.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;  //쿼리 수행 객체
	
	//목록 가져오기
	public List selectAll() {
		List list = null;
		//Spring이 transaction을 주도하기 때문에 Connection, PreparedStatement, ResultSet 등
		//관련 객체를 이 안에서 처리하지 않아도 된다
		String sql = "select * from notice order by notice_id desc";

		//매개변수 순서 : (쿼리문, 바인드변수를 배열로 처리, 매칭객체-ResultSet의 데이터를 담는 객체)
		list = jdbcTemplate.query(sql, new Object[] {}, new RowMapper<Notice>() {
			@Override
			public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
				logger.debug("mapRow메서드 호출. rowNum = " + rowNum);

				Notice notice = new Notice();  //create empty vo
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setTitle(rs.getString("title"));
				notice.setWriter(rs.getString("writer"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));

				return notice;
			}
		});
		
		return list;
	}

	@Override
	public Notice select(int notice_id) {
		Notice notice = null;
		String sql = "select * from notice where notice_id=?";
		notice = jdbcTemplate.queryForObject(sql, new RowMapper<Notice>() {

			@Override
			public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
				Notice notice = new Notice();  //create empty vo
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setTitle(rs.getString("title"));
				notice.setWriter(rs.getString("writer"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));

				return notice;
			}
			
		}, notice_id);
		
		return notice;
	}

	//jdbcTemplate에서 DML은 update 메서드로 처리
	@Override
	public void insert(Notice notice) throws DMLException {
		String sql = "insert into notice(notice_id, title, writer, content)";
		sql += " values(seq_notice.nextval, ?, ?, ?)";
		int result = jdbcTemplate.update(sql, notice.getTitle(), notice.getWriter(), notice.getContent());
		//result = 0;  //예외처리가 잘 되었는지 확인하기 위해 일부러 등록 실패로 처리
		if(result == 0) {
			throw new DMLException("등록에 실패하였습니다");  //인위적인 runtime error 발생시킴
		}
	}

	@Override
	public void update(Notice notice) throws DMLException {
		String sql = "update notice set title=?, writer=?, content=? where notice_id=?";
		int result = jdbcTemplate.update(sql, notice.getTitle(), notice.getWriter(), notice.getContent(), notice.getNotice_id());
		//result = 0;  //예외처리가 잘 되었는지 확인하기 위해 일부러 등록 실패로 처리
		if(result == 0) {
			throw new DMLException("수정에 실패했습니다");
		}
	}

	@Override
	public void delete(int notice_id) throws DMLException {
		String sql = "delete from notice where notice_id=?";
		int result = jdbcTemplate.update(sql, notice_id);
		//result = 0;  //예외처리가 잘 되었는지 확인하기 위해 일부러 등록 실패로 처리
		if(result == 0) {
			throw new DMLException("삭제에 실패했습니다");
		}
	}

}
