package com.koreait.restproject.rest.websocket;

import lombok.Data;

//정형화된 데이터 구조로 웹소켓 데이터를 주고받자!!
@Data
public class SocketMessage {
	private String requestCode; //create, read ,update ,delete 
	private int resultCode; //200 성공여부 코드 (우리가 정하기 나름..) 
	private String msg; //담고 싶은 메시지 
	private String data; //json을 심을 변수 
}
