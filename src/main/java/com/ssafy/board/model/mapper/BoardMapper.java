package com.ssafy.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.FreeBoardDto;

@Mapper
public interface BoardMapper {
	List<BoardDto> selectAllBoard();
	List<FreeBoardDto> selectFreeBoard();
	List<FreeBoardDto> searchFreeBoard(String keyword);
	
	void insertFreeBoard(FreeBoardDto fbDto);
}
