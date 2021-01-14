package com.koreait.fashionshop.client.aop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.fashionshop.model.product.service.TopCategoryService;

//쇼핑몰 이용시 전반적으로 사용할 카테고리 메뉴 목록

public class GlobalDataAspect {
	@Autowired
	private TopCategoryService topCategoryService;
	
	public Object getGlobalData(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = null;
		HttpServletRequest request = null;
		
		//매개 변수로부터 요청 객체 추출
		for(Object arg : joinPoint.getArgs()) {
			if(arg instanceof HttpServletRequest) {
				request = (HttpServletRequest)arg;
			}
		}
		
		String uri = request.getRequestURI();  //클라이언트의 요청 URI String 정보
		//topList를 저장해야하는 경우와 그렇지 않은 경우를 나누어서 처리
		if(uri.equals("/shop/member/login") || 
			uri.equals("/shop/member/regist") ||
			uri.equals("/admin") ||
			uri.equals("/admin/product/regist") ||
			uri.equals("/admin/product/excel/registform")
		) {  //클라이언트의 요청이 topList를 필요로하지 않는 경우 : /shop/member/login, /shop/member/regist
			result = joinPoint.proceed();  //원래 호출하려했던 메서드 호출. go ahead!
		} else {  //필요한 경우
			//topList가져오기
			List topList = topCategoryService.selectAll();
			Object returnObj = joinPoint.proceed();  //원래 호출하려했던 메서드 호출
			ModelAndView mav = null;
			if(returnObj instanceof ModelAndView) {
				mav = (ModelAndView)returnObj;
				mav.addObject("topList", topList);
				result = mav;
			}
		}
		
		
		
		return result;
	}
}
