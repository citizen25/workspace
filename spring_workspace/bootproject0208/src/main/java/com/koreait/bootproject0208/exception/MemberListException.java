package com.koreait.bootproject0208.exception;

public class MemberListException  extends RuntimeException{
	
	public MemberListException(String msg) {
		super(msg);
	}
	public MemberListException(String msg, Throwable e) {
		super(msg,e);
	}
	public MemberListException(Throwable e) {
		super(e);
	}	
}
