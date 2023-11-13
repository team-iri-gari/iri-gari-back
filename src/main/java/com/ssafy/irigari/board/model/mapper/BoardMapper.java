package com.ssafy.irigari.board.model.mapper;

import java.util.List;

import com.ssafy.irigari.board.model.BoardDto;
import com.ssafy.irigari.board.model.FreeBoardDto;

public interface BoardMapper {
	List<BoardDto> selectAllBoard();
	List<FreeBoardDto> selectFreeBoard();
	
	void insertFreeBoard(FreeBoardDto fbDto);
}
