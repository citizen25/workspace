package com.koreait.restproject.model.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.restproject.exception.MemberListException;
import com.koreait.restproject.exception.MemberUpdateException;
import com.koreait.restproject.model.domain.Member;
import com.koreait.restproject.model.member.repository.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public List<Member> selectAll() {
		return memberDAO.selectAll();
	}

	@Override
	public Member select(int member_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void regist(Member member) throws MemberUpdateException {
		memberDAO.insert(member);
	}

	@Override
	public void update(Member member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int member_id) {
		// TODO Auto-generated method stub
		
	}

}
