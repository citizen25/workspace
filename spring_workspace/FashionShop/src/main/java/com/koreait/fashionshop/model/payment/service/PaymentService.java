package com.koreait.fashionshop.model.payment.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.model.domain.Cart;

public interface PaymentService {
	//장바구니 관련 업무
	public List selectCartList();  //회원 구분없이 모든 데이터 가져오기
	public List selectCartList(int member_id);  //특정 회원의 장바구니 내역
	public Cart selectCart(int cart_id);
	public void update(Cart cart);
	public void insert(Cart cart);
	public void delete(Cart cart);  //회원 id에 속한 데이터 삭제할 예정	

	//결제 업무
	
	
}
