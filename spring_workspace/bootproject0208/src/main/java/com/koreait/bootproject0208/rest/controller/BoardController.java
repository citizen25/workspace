package com.koreait.bootproject0208.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.bootproject0208.model.domain.Board;
import com.koreait.bootproject0208.test.Dog;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BoardController {

	@Autowired
	private Dog dog;

	@GetMapping("/rest/board")
	public ResponseEntity<List<Board>> getList() {    // json이다. Legacy에서는 json으로 바꾸려면? converter 등록해야 함
		log.info("목록을 원해");
		
		// jackson 설치 밑 설정해야 하는데 Spring Boot는 알아서 다 해준다
		List<Board> boardList = new ArrayList();    // 원래는 boardService.selectAll() 이렇게 해야하지만..
		Board board = new Board();
		board.setBoard_id(1);
		board.setTitle(dog.getName());
		board.setWriter("홍길동");
		board.setContent("안녕안녕");
		board.setRegdate("2021-02-18");
		board.setHit(25);

		boardList.add(board);
		
		// 만일 컨버터가 없었다면? Gson을 이용해서 jsonString을 만들어 출력. 
		// 그러나 conveter를 이용하면 이런 과정조차 불필요
		
		return ResponseEntity.ok().body(boardList);
		
	}

}
