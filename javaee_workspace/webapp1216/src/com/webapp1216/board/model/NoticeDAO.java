package com.webapp1216.board.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.webapp1216.mybatis.config.MybatisConfigManager;

public class NoticeDAO {
	MybatisConfigManager manager = MybatisConfigManager.getInstance();  //singleTon 객체 얻기
	
	public int insert(Notice notice) {
		int result = 0;
		//jdbc가 아닌 mybatis로 퀴리문 수행하자
		SqlSession sqlSession = manager.getSqlSession();  //쿼리담당 객체 (xml의 쿼리문 호출 가능)
		result = sqlSession.insert("Notice.insert", notice);  //얘는 Notice의 insert야
		sqlSession.commit();  //DML인 경우 commit 처리
		manager.close(sqlSession);
		return result;
	}
	
	public List selectAll() {
		List list = null;
		SqlSession sqlSession = manager.getSqlSession();
		list = sqlSession.selectList("Notice.selectAll");
		manager.close(sqlSession);
		return list;
	}
	
	public Notice select(int notice_id) {
		Notice notice = null;
		SqlSession sqlSession = manager.getSqlSession();
		notice = sqlSession.selectOne("Notice.select", notice_id);
		manager.close(sqlSession);
		return notice;
	}
	
	public int update(Notice notice) {
		int result = 0;
		SqlSession sqlSession = manager.getSqlSession();
		result = sqlSession.delete("Notice.update", notice);
		sqlSession.commit();  //DML이므로 commit()
		manager.close(sqlSession);
		return result;
	}
	
	public int delete(int notice_id) {
		int result = 0;
		SqlSession sqlSession = manager.getSqlSession();
		result = sqlSession.delete("Notice.delete", notice_id);
		sqlSession.commit();  //DML이므로 commit()
		manager.close(sqlSession);
		return result;
	}
	
}
