package com.koreait.fashionshop.model.product.service;

import java.util.List;

import com.koreait.fashionshop.model.domain.SubCategory;

public interface SubCategoryService {
	abstract public List selectAll();  //모든 레코드 가져오기
	abstract public List selectAllById(int topcategory_id);  //선택한 상위 카테고리에 소속된 하위 카테고리의 항목 가져오기
	abstract public SubCategory select(int topcategory_id);
	abstract public void insert(SubCategory subcategory);
	abstract public void update(SubCategory subcategory);
	abstract public void delete(int topcategory_id);
}
