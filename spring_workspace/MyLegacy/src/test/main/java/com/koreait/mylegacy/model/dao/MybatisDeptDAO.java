package com.koreait.mylegacy.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.koreait.mylegacy.exception.RegistException;
import com.koreait.mylegacy.model.domain.Dept;

@Repository
public class MybatisDeptDAO {
	private SqlSession sqlSession = null;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//1건 등록
	public int insert(Dept dept) throws RegistException {
		int result = 0;
		result = sqlSession.insert("Dept.insert", dept);  //emp안에 dept가 포함되어있다
		//result = 0;  //일부러 실패로 간주해본다 -> 둘다 들어가지 말아야 한다
		//만일 부서등록이 실패하면 여기서 억지로 예외를 발생시켜버리자
		if(result == 0) {
			//java에서는 에러를 억지로 발생시켜주는 기능이 지원된다
			throw new RegistException("부서 등록에 실패하였습니다");
		}
		
		return result;
	}

}
