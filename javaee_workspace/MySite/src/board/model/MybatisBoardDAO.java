//기존의 전통적인 JDBC 방식으로 작성했던 DAO의 CRUD 메서드를 mybatis 프레임웍을 이용하여
//코드를 간략하게 만들어 보자

package board.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis.config.MybatisConfigManager;

public class MybatisBoardDAO {
	
	MybatisConfigManager configManager = MybatisConfigManager.getInstance();
		//아무리 호출해도 하나만 올라온다 (SingleTon)
	
	public int insert(Board board) {
		int result = 0;
		SqlSession sqlSession = configManager.getSqlSession();
		result = sqlSession.insert("mybatis.mappers.BoardMapper.insert", board);
		sqlSession.commit();  //DML의 경우 commit을 해주어야 한다
		configManager.close(sqlSession);
		return result;
	}
	
	public List selectAll() {
		List list = null;
		SqlSession sqlSession = configManager.getSqlSession();  //쿼리 수행 객체 얻어오기
		list = sqlSession.selectList("mybatis.mappers.BoardMapper.selectAll");		
		configManager.close(sqlSession);
		return list;
	}
	
	public Board select(int board_id) {
		Board board = null;
		SqlSession sqlSession = configManager.getSqlSession();
		board = sqlSession.selectOne("mybatis.mappers.BoardMapper.select", board_id);
		configManager.close(sqlSession);
		return board;
	}

	public int update(Board board) {
		int result = 0;
		SqlSession sqlSession = configManager.getSqlSession();
		result = sqlSession.update("mybatis.mappers.BoardMapper.update", board);
		sqlSession.commit();
		configManager.close(sqlSession);
		return result;
	}
	
	public int delete(int board_id) {
		int result = 0;
		SqlSession sqlSession = configManager.getSqlSession();
		result = sqlSession.update("mybatis.mappers.BoardMapper.delete", board_id);
		sqlSession.commit();
		configManager.close(sqlSession);
		return result;
	}
	
}
