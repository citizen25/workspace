//�� Ŭ������ ����
//������ ���࿡ �ʿ��� SqlSession�� ���� ���� �� �� �ֵ���
//���뼺�� ����Ͽ� ������ ��ü
//Ư��, �� ��ü�� instance�� Application���� 1���� �־��ϹǷ�,
//SingleTon�������� �����Ѵ�

package com.study.springfinal.mybatis.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;

@Component
public class MybatisConfigManager {
	//2) �����ڸ� �������Ƿ�, ���� class���� instance�� �������� ������ ����� ����� ������
	//���� ���� class���� getter�� ����������
	private static MybatisConfigManager instance;
	InputStream inputStream; 
	SqlSessionFactory sqlSessionFactory; 
	
	//1) private���� �����ڸ� ���, �ƹ��� new ���ϰ� �Ѵ�
	private MybatisConfigManager() {
		String resource = "com/study/springfinal/mybatis/config/config.xml";
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	//3) getter�� ����������, ���� instance �޼��������, new �Ѵ� �������� ȣ���� �� �ִ�
	//���� �� � ��ü�ε� �� �޼��带 ȣ���� �� ����(new�� ���Ƴ������ϱ�)
	//�ذ�å) new���� �ʰ� �Ʒ��� �޼��带 ȣ���� �� �ֵ��� static �޼���� ��������
	public static MybatisConfigManager getInstance() {
		//4) �� �޼��� ȣ�� �� instance ������ null�̶��,
		//���⼭ instance�� �����ؼ� null�� �ƴ� ���� ���������� ó������
		if(instance == null) {
			instance = new MybatisConfigManager();
		}
		
		return instance;
	}
	
	//SqlSession ���, �ݱ�
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
