package com.koreait.mvclegacy.controller.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.mvclegacy.exception.DMLException;
import com.koreait.mvclegacy.model.domain.Notice;
import com.koreait.mvclegacy.model.notice.NoticeService;

@Controller
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired
	private NoticeService noticeService;
	
	//글쓰기 폼 요청
	@RequestMapping("/notice/registform")
	public String getRegistForm() {
		return "notice/regist_form";
	}
	
	//글 등록 요청 처리
	@RequestMapping(value="/notice/regist", method=RequestMethod.POST)
	public String regist(Notice notice) {  //Spring에서는 vo의 멤버변수와 html의 파라미터명이 같으면 자동으로 데이터값이 vo에 채워진다
		noticeService.insert(notice);  //여기서 예외가 발생하면, 실행부는 아래 명시한 예외 핸들러 메서드를 호출
		return "redirect:/client/notice/list";
	}

	//모든 글 가져오기
	@RequestMapping(value="/notice/list", method=RequestMethod.GET)
	public ModelAndView selectAll() {
		logger.debug("글 목록 메서드 호출");
		ModelAndView mav = new ModelAndView();
		List noticeList = noticeService.selectAll();
		mav.addObject("noticeList", noticeList);
		mav.setViewName("notice/list");
		return mav;
	}
	
	//한건 가져오기
	@RequestMapping(value="/notice/detail", method=RequestMethod.GET)
	public ModelAndView select(int notice_id) {
		Notice notice = noticeService.select(notice_id);
		ModelAndView mav = new ModelAndView("notice/detail");
		mav.addObject("notice", notice);
		return mav;
	}

	//수정 요청 처리
	@RequestMapping(value="/notice/edit", method=RequestMethod.POST)
	public ModelAndView update(Notice notice) {
		ModelAndView mav = new ModelAndView();
		try {
			noticeService.update(notice);
			//mav.addObject("msg", "등록 성공");
			mav.setViewName("redirect:/client/notice/detail?notice_id=" + notice.getNotice_id());
		} catch (DMLException e) {
			mav.addObject("msg", e.getMessage());
			mav.setViewName("message/result");
			e.printStackTrace();
		}

		return mav;
	}
	
	//삭제 요청 처리
	@RequestMapping(value="/notice/del", method=RequestMethod.POST)
	public String delete(int notice_id) {
		noticeService.delete(notice_id);
		return "redirect:/client/notice/list";
	}
	
	//Spring controller의 요청 처리 메서드 중, 어느 하나라도 예외가 발생하면
	//그 예외를 처리할 수 있는 별도의 메서드가 지원된다
	//에러가 발생하면 event를 잡아낸다 -> 에러를 모아서 처리할 수 있따
	//Annotation에 명시한 예외만 감지하여 처리한다
	@ExceptionHandler(DMLException.class)
	public ModelAndView handlerException(DMLException e) {
		ModelAndView mav = new ModelAndView();
		
		//어떤 내용을 담을지? 에러 메세지를 담자
		mav.addObject("msg", e.getMessage());
		
		//어느 페이지를 보여줄지? message/result.jsp
		mav.setViewName("message/result");
		
		return mav;
	}
	

}












