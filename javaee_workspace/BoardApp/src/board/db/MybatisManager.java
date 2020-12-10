package board.db;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisManager {
	private SqlSessionFactory sqlSessionFactory; //SqlSession의 인스턴스를 얻기 위한 팩토리 객체
	
	public MybatisManager() {
		String resource = "mybatis/config/config.xml";
		InputStream inputStream;
		
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			System.out.println(sqlSessionFactory);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
	public static void main(String[] args) {
		new MybatisManager();
	}
}