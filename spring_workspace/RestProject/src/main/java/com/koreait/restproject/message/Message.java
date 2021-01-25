package com.koreait.restproject.message;

import lombok.Data;

@Data
public class Message {
	private String msg;    // 클라이언트가 받게 될 error 메세지
	private int statusCode;    // http 상태 코드 (ex. 200, 404, 500, 403, etc..)
	
}
