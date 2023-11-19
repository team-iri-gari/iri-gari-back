package com.ssafy.board.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.FileInfoDto;
import com.ssafy.board.model.FormDto;
import com.ssafy.board.model.FreeBoardDto;

public interface BoardService {
	List<BoardDto> selectAllBoard();
	List<?> selectBoardType(String type);
	List<?> searchBoard(String type, String keyword);
	
	void insertFreeBoard(FreeBoardDto fbDto);
	void insertPlanBoard(BoardDto board, FormDto fdto, MultipartFile[] files) throws Exception;
	
	FreeBoardDto selectBoardId(int id);
	List<FileInfoDto> getPhotosByPostId(int id);
	
}
