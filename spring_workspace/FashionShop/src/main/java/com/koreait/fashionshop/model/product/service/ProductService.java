package com.koreait.fashionshop.model.product.service;

import java.util.List;

import com.koreait.fashionshop.model.domain.Product;

public interface ProductService {

	abstract public List selectAll(); // 모든 상품
	abstract public List selectById(int subcategory_id); // 하위 카테고리에 소속된 모든 상품
	abstract public Product select(int product_id);
	abstract public void regist(Product product);  //insert 하는 것이 아니기 때문에 regist로 명명
	abstract public void update(Product product);
	abstract public void delete(int product_id);
}
