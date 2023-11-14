package com.ssafy.board.model.mapper;

import java.util.List;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.FreeBoardDto;

public interface BoardMapper {
	List<BoardDto> selectAllBoard();
	List<FreeBoardDto> selectFreeBoard();
	
	void insertFreeBoard(FreeBoardDto fbDto);
}
