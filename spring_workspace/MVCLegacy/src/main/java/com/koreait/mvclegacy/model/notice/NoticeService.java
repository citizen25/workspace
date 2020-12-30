package com.koreait.mvclegacy.model.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.koreait.mvclegacy.exception.DMLException;
import com.koreait.mvclegacy.model.domain.Notice;

@Service
public class NoticeService {
	//주입시키려는 자료형이 2개이상일 경우, 개발자는 무엇을 사용할지 원하는 객체를 명시해야 한다
	@Autowired
	@Qualifier("jdbcNoticeDAO")  //Mybatis, Jdbc NoticeDAO가 겹치기 때문에 Qualifier 준다
	private NoticeDAO noticeDAO;  //DI로 주입받기 위해서 상위 객체를 보유한다

	//CRUD method

	public List selectAll() {
		List list = noticeDAO.selectAll();
		return list;
	}
	
	public Notice select(int notice_id) {
		Notice notice = noticeDAO.select(notice_id);
		return notice;
	}
	
	public void insert(Notice notice) throws DMLException {
		noticeDAO.insert(notice);
	}

	public void update(Notice notice) throws DMLException {
		noticeDAO.update(notice);
	}

	public void delete(int notice_id) {
		noticeDAO.delete(notice_id);
	}
}
