//mybatis의 config.xml은 xml일 뿐, 현재 실행 중인 java application과는 상관 없는 상태이다
//따라서 자바 코드에서 config.xml을 읽어들여야 한다
//최종 목표 : xml을 들여서, 실제 쿼리문을 수행해주는 객체인 SqlSession을 얻기 위함
//이 클래스는 new 할때마다 민스턴스가 생성도리 것이고, 그렇게 되면, SqlSessionFactory로 
//다수가 메모리에 올라오므로, 메모리 누수가 될 것임. 따라서 SingleTon으로 정의하자

package com.webapp1216.mybatis.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConfigManager {
	String resource = "com/webapp1216/mybatis/config/config.xml";
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;
	private static MybatisConfigManager instance;  //instance 명명 : 전세계 개발자들의 약속된 습관?
	
	private MybatisConfigManager() {		
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	public static MybatisConfigManager getInstance() {
		if(instance == null) {
			instance = new MybatisConfigManager();
				//아무리 private이라도 나의 생성자는 내가 호출 가능함
		}
		return instance;
	}
	
	//SqlSession을 반환하는 메서드
	public SqlSession getSqlSession() {
		SqlSession sqlSession = null;
		sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
	
	public void close(SqlSession sqlSession) {
		if(sqlSession != null) {
			sqlSession.close();			
		}
	}
	
}
