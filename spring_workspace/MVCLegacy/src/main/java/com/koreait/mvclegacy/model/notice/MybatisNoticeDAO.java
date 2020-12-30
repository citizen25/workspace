package com.koreait.mvclegacy.model.notice;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.mvclegacy.exception.DMLException;
import com.koreait.mvclegacy.model.domain.Notice;

@Repository
public class MybatisNoticeDAO implements NoticeDAO {
	//Spring이 지원하는 DB기술을 이용하기 전에는 transcation 때문에
	//SqlSession을 서비스로부터 주입받았으나, 더이상 transaction 때문에
	//서비스에서 주입받을 필요가 없다.
	//Spring이 알아서 transaction을 처리해준다
	
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	public List selectAll() {
		List list = null;
		list = sessionTemplate.selectList("Notice.selectAll");
		return list;
	}
	
	public Notice select(int notice_id) {
		Notice notice = null;
		notice = sessionTemplate.selectOne("Notice.select", notice_id);
		return notice;
	}
	
	public void insert(Notice notice) {
		sessionTemplate.insert("Notice.insert", notice);
	}

	public void update(Notice notice) throws DMLException {
		int result = sessionTemplate.update("Notice.update", notice);
		result = 0;  //억지로 대입. 실패 유도
		if(result == 0) {
			throw new DMLException("수정 작업에 실패하였습니다.");
		}
	}

	public void delete(int notice_id) {
		sessionTemplate.delete("Notice.delete", notice_id);
	}
}
