package com.koreait.fashionshop.model.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.fashionshop.common.MailSender;
import com.koreait.fashionshop.common.SecureManager;
import com.koreait.fashionshop.exception.MailSendException;
import com.koreait.fashionshop.exception.MemberRegistException;
import com.koreait.fashionshop.model.domain.Member;
import com.koreait.fashionshop.model.member.repository.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	//메일 발송 객체
	@Autowired
	private MailSender mailSender;
	
	//암호화 객체
	@Autowired
	private SecureManager secureManager;


	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void regist(Member member) throws MemberRegistException, MailSendException {
		//DB에 넣기  + email 보내가 + 문자 발성 등등
		
		//암호화 처리
		String secureData = secureManager.getSecureData(member.getPassword());
		member.setPassword(secureData);  //변환시켜 다시 VO에 대입
		
		memberDAO.insert(member);

		String name = member.getName();
		String addr = member.getAddr();
		String email = member.getEmail_id() + "@" + member.getEmail_server();
		
		mailSender.send(email, name + "님 [패션샵]가입 축하드려요", addr + "에 거주하시는군요. 감사합니다");
	}

	@Override
	public void update(Member member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Member member) {
		// TODO Auto-generated method stub
		
	}

}
