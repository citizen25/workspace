package com.koreait.fashionshop.model.product.service;

import java.util.List;

import com.koreait.fashionshop.model.domain.TopCategory;

public interface TopCategoryService {

	//모든 하위 객체가 반드시 구현해야할 공통 및 필수적인 기능을 정의

	//CRUD
	abstract public List selectAll();
	abstract public TopCategory select(int topcategory_id);
	abstract public void insert(TopCategory topcategory);
	abstract public void update(TopCategory topcategory);
	abstract public void delete(int topcategory_id);
}
