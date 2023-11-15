package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.FreeBoardDto;

public interface BoardService {
	List<BoardDto> selectAllBoard();
	List<FreeBoardDto> selectFreeBoard();
	List<FreeBoardDto> searchFreeBoard(String keyword);
	
	void insertBoard(FreeBoardDto fbDto);
	
}
