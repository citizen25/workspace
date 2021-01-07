package com.koreait.fashionshop.model.member.service;

import java.util.List;

import com.koreait.fashionshop.model.domain.Member;

public interface MemberService {
	public List selectAll();  //모든 회원 가져오기
	public Member select(Member member);  //회원 1명 가져오기
	public void regist(Member member);  //회원 등록 및 기타 필요사항 처리 -> insert는 의미가 약하다. db에 넣는 것 외에도 파라미터를 받고 보내는 역할까지 한다
	public void update(Member member);  //회원 정보 수정
	public void delete(Member member);  //회원 정보 삭제

}
