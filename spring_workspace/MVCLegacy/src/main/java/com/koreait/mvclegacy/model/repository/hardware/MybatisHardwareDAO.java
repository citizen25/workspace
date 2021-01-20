package com.koreait.mvclegacy.model.repository.hardware;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.mvclegacy.model.domain.Hardware;

@Repository
public class MybatisHardwareDAO implements HardwareDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Hardware> selectAll() {
		return sqlSessionTemplate.selectList("Hardware.selectAll");
	}
	
}
