package com.ssafy.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.FileInfoDto;
import com.ssafy.board.model.FreeBoardDto;
import com.ssafy.board.model.PlanBoardDto;

@Mapper
public interface BoardMapper {
	List<BoardDto> selectAllBoard();
	List<FreeBoardDto> selectFreeBoard();
	List<PlanBoardDto> selectPlanBoard();	// 전체 여행계획 게시판 만들 때 보일 게시판 목록
	List<FreeBoardDto> searchFreeBoard(String[] words);
	List<PlanBoardDto> searchPlanBoard(String[] words);	// Search View에서 보일 검색 목록
	
	void insertBoard(BoardDto bDto);
	void insertFreeBoard(FreeBoardDto fbDto);
	void insertPlanBoard(PlanBoardDto pbDto);	// 여행 계획 게시물 추가
	void registFileInfo(FreeBoardDto fbDto);
	
	FreeBoardDto selectBoardId(int id);
	List<FileInfoDto> getPhotosByPostId(int postId);
}
