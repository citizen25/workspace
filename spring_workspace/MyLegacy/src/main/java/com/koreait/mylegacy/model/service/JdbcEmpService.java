package com.koreait.mylegacy.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.mylegacy.model.dao.JdbcDeptDAO;
import com.koreait.mylegacy.model.dao.JdbcEmpDAO;
import com.koreait.mylegacy.model.domain.Dept;
import com.koreait.mylegacy.model.domain.Emp;
import com.koreait.mylegacy.model.pool.PoolManager;

//MVC에서 Model영역의 서비스를 정의한다 (model이지만 일을 시킨다)
//서비스는 직접 로직을 수행하지는 않으며, model 영역의 각 업무를 수행하는 객체를 
//제어하는 역할. 만일 Service의 존재가 없다면, controller가 model영역의
//복잡한 처리를 직접해야하므로, application 설계상 영역 구분이 약해지게 된다

@Service
public class JdbcEmpService {
	@Autowired
	private PoolManager poolManager;

	@Autowired
	private JdbcDeptDAO jdbcDeptDAO;
	
	@Autowired
	private JdbcEmpDAO jdbcEmpDAO;
	
	//글 목록
	public List selectAll() {
		List list = null;
		Connection con = poolManager.getConnection();
		jdbcEmpDAO.setCon(con);  //Connection 주입
		list = jdbcEmpDAO.SelectAll();
		poolManager.freeConnection(con);
		
		return list;
	}
	
	//한 건(부서, 사원)
	public Dept selectDept(int deptno) {
		Dept dept = null;
		
		return dept;
	}
	public Emp selectEmp(int empno) {
		Emp  emp = null;
		
		return emp;
	}
	
	//사원등록(부서와 사원의 트랜잭션 관계)
	public int regist(Emp emp) {
		int result = 0;
		Connection con = poolManager.getConnection();
		
		//DAO들에게 동일한 Connection을 배분
		jdbcDeptDAO.setCon(con);
		jdbcEmpDAO.setCon(con);
		
		try {
			con.setAutoCommit(false);  //자동 커밋 방지
			jdbcDeptDAO.regist(emp.getDept());  //부서 등록
			jdbcEmpDAO.regist(emp);
			
			con.commit();
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();  //error가 하나라도 발생한다면 transaction rollback
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				con.setAutoCommit(true);  //다시 true로 돌려놓기
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		poolManager.freeConnection(con);
		
		return result;
	}
	
	//수정
	public int updateDept(Dept dept) {
		int result = 0;
		
		return result;
	}
	public int updateEmp(Emp emp) {
		int result = 0;
		
		return result;
	}
	
	//삭제
	public int deleteDept(int deptno) {
		int result = 0;
		
		return result;
	}
	public int deleteEmp(int empno) {
		int result = 0;
		
		return result;
	}
}
