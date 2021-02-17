package com.koreait.bootproject0208;

import javax.sql.DataSource;

import org.apache.catalina.core.ApplicationContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 원래는 메인 클래스에서 작덩해도 되지만, 관리 목적상 별도의 클래스로 분리시켜보았다
// 스프링의 버전이 올라갈 수록 xml --> 자바 코드에서의 설정으로 옮겨감

@Configuration    // Spring의 설정 파일을 대신한다는 의미
public class MybatisConfigManager {
	// Spring의 Beam을 관리하는 객체
	private ApplicationContext applicationContext;
	
	
	// Legacy 시절 등록했던 mybatis spring에 대한 설정을 여기서 처리해보자
	// SqlSessionFactory, SqlSessionTemplate 등등.
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		
		sqlSessionFactory.setDataSource(dataSource);
		// mybaytis 설정파일 위치
		sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:com/koreait/bootproject0208/mybatis/config/config.xml"));
		sqlSessionFactory.setConfiguration(mybatisConfig());
		
		return sqlSessionFactory.getObject();
	}
	
	@Bean
	public org.apache.ibatis.session.Configuration mybatisConfig() {
		return new org.apache.ibatis.session.Configuration();
	}

	
}
