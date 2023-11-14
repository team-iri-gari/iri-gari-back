package com.ssafy.board.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.FreeBoardDto;
import com.ssafy.board.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;
	
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Override
	public List<BoardDto> selectAllBoard() {
		return boardMapper.selectAllBoard();
	}

	@Override
	public List<FreeBoardDto> selectFreeBoard() {
		return boardMapper.selectFreeBoard();
	}

	@Override
	public void insertFreeBoard(FreeBoardDto fbDto) {
		
	}

	@Override
	public List<FreeBoardDto> searchFreeBoard(String keyword) {
		return boardMapper.searchFreeBoard(keyword);
	}

}
