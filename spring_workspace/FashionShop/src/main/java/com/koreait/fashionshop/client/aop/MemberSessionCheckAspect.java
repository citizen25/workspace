package com.koreait.fashionshop.client.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koreait.fashionshop.exception.LoginRequiredException;

//앞으로 로그인이 필요한 서비스 여부를 처리하기 위한 코드는, 컨트롤러에 두지 않고
//지금 이 객체로 공통화시켜 AOP를 적용하겠다 (개발자들에겐 꿈의 기술)

public class MemberSessionCheckAspect {
	private static final Logger logger = LoggerFactory.getLogger(MemberSessionCheckAspect.class);
	
	//ProseedingJoinPoint에 요청 흐름을 부드럽게 처리할 수 있게 하기 위한 정보가 모두 들어있다
	//원래 호출하려했던 객체, 메서드의 이름, 매개변수, 매개변수 갯수, .. 등등
	public Object sessionCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		Object target = joinPoint.getTarget();  //원래 호출하려고 했던 객체
		logger.debug("원래 호출하려했던 객체는 " + target);
		String methodName = joinPoint.getSignature().getName();
		logger.debug("원래 호출하려했던 메서드는 " + methodName);
		Object[] args = joinPoint.getArgs();  //원래 호출하려했던 메서드의 메개변수
		
		//세션을 얻기 위해서는 HttpServletReqeust가 필요하다
		HttpServletRequest request = null;
		
		for(Object arg : args) {
			logger.debug("매개변수명은 " + arg);
			if(arg instanceof HttpServletRequest) {  //request 객체라면
				request = (HttpServletRequest)arg;
			}
		}
		
		//현재 요청이 세션을 가지고 있는지를 판단하여, 적절한 제어
		//1. 세션이 없다면?? 예외를 발생시킬 것이다 -> ExceptionHandler.를 거쳐서 클라이언트에 적절한 응답처리
		//2. 세션이 있다면? 그대로 원래 호출하려했던 메서드 진행기켜주자
		HttpSession session = null;
		session = request.getSession();
		Object result = null;
		
		if(session.getAttribute("member") == null) {
			throw new LoginRequiredException("로그인이 필요한 서비스입니다.");
		} else {
			//원래 요청의 흐름을 그대로 진행
			//원래 호출하려했던 메서드를 대신 호출
			result = joinPoint.proceed();  //중간에 끼어들고, 그대로 지나가게 한다. 여기서 예외가 발생하므로 예외처리하지 말고, throws하자
				//mainController에서 할 일을 .proceed()에서 하고 반환되는 ModelAndView를 반환할 수 있다
		}
		

		return result;
	}

}
