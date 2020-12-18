package com.model2.notice.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.model2.mybatis.config.MybatisConfigManager;
import com.model2.notice.domain.Notice;

public class NoticeDAO {
	MybatisConfigManager manager = MybatisConfigManager.getInstance();
	
	public List selectAll() {
		List list = null;
		SqlSession sqlSession = manager.getSqlSession();
		list = sqlSession.selectList("Notice.selectAll");  //xml의 아이디
		manager.close(sqlSession);
		return list;
	}
	
	public int insert(Notice notice) {
		int result = 0;
		SqlSession sqlSession = manager.getSqlSession();
		result = sqlSession.insert("Notice.insert", notice);
		sqlSession.commit();  //DML이므로 commit
		manager.close(sqlSession);
		return result;
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
		
		return result;
	}
	
	public int delete(int notice_id) {
		int result = 0;
		
		return result;
	}

}
