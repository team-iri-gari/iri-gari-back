package com.ssafy.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.FileInfoDto;
import com.ssafy.board.model.FreeBoardDto;

@Mapper
public interface BoardMapper {
	List<BoardDto> selectAllBoard();
	List<FreeBoardDto> selectFreeBoard();
	List<FreeBoardDto> searchFreeBoard(String[] words);
	
	void insertBoard(BoardDto bDto);
	void insertFreeBoard(FreeBoardDto fbDto);
	void registFileInfo(FreeBoardDto fbDto);
	
	FreeBoardDto selectBoardId(int id);
	List<FileInfoDto> getPhotosByPostId(int postId);
}
