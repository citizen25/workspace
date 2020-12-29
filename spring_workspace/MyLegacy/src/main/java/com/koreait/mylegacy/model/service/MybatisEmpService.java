package com.koreait.mylegacy.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.mylegacy.exception.RegistException;
import com.koreait.mylegacy.mabatis.config.MybatisConfigManager;
import com.koreait.mylegacy.model.dao.MybatisDeptDAO;
import com.koreait.mylegacy.model.dao.MybatisEmpDAO;
import com.koreait.mylegacy.model.domain.Emp;

@Service
public class MybatisEmpService {

	@Autowired
	private MybatisConfigManager manager;

	@Autowired
	private MybatisEmpDAO mybatisEmpDAO;
	
	@Autowired
	private MybatisDeptDAO mybatisDeptDAO;
	
	//모든 사원 레코드 가져오기
	public List selectAll() {
		List list = null;
		SqlSession sqlSession = manager.getSqlSession();
		mybatisEmpDAO.setSqlSession(sqlSession);
		list = mybatisEmpDAO.selectAll();
		manager.close(sqlSession);
		return list;
	}
	
	//사원 등록(부서 등록 + 사원 등록 = 2개의 업무로 구성된 트랜잭션 상황)
	public int regist(Emp emp) {
		int result = 0;
		//일 시키지 전에 SqlSession 배분
		SqlSession sqlSession = manager.getSqlSession();  //commit default가 false
		mybatisEmpDAO.setSqlSession(sqlSession);
		mybatisDeptDAO.setSqlSession(sqlSession);

		//아래의 두 DML 메서드를 트랜잭션 대상으로 commit/rollback
		try {
			result = mybatisEmpDAO.insert(emp);  //트랜잭션 대상
			result = mybatisDeptDAO.insert(emp.getDept());  //트랜잭션 대상
			sqlSession.commit();
		} catch (RegistException e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		
		manager.close(sqlSession);
		return result;
	}
}
