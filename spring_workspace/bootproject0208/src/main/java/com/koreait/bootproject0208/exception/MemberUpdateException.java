package com.koreait.bootproject0208.exception;

public class MemberUpdateException extends RuntimeException{
	
	public MemberUpdateException(String msg) {
		super(msg);
	}
	public MemberUpdateException(String msg, Throwable e) {
		super(msg,e);
	}
	public MemberUpdateException(Throwable e) {
		super(e);
	}	
}
