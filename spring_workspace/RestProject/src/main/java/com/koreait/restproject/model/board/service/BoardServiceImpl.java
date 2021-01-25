package com.koreait.restproject.model.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.restproject.model.board.repository.BoardDAO;
import com.koreait.restproject.model.domain.Board;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;

	@Override
	public List<Board> selectAll() {
		return boardDAO.selectAll();
	}

	@Override
	public Board select(int board_id) {
		// TODO Auto-generated method stub
		return boardDAO.select(board_id);
	}

	@Override
	public void regist(Board board) {
		// TODO Auto-generated method stub
		boardDAO.insert(board);
	}

	@Override
	public void update(Board baord) {
		// TODO Auto-generated method stub
		boardDAO.update(baord);;
	}

	@Override
	public void delete(int board_id) {
		// TODO Auto-generated method stub
		boardDAO.delete(board_id);
	}

}
