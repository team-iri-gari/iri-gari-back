package com.ssafy.board.model.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.FileInfoDto;
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
	public List<?> selectBoardType(String type) {
		if (type.equals("free")) {
			return boardMapper.selectFreeBoard();			
		} else if (type.equals("plan")) {
			return null;
		}
		return null;
	}

	@Override
	public List<?> searchBoard(String type, String keyword) {
		String[] words = keyword.split(" "); 
		System.out.println(Arrays.toString(words));
		
		if (type.equals("free")) {
			return boardMapper.searchFreeBoard(words);			
		} else if (type.equals("plan")) {
			return null;
		}
		return null;
	}

	@Override
	@Transactional
	public void insertBoard(FreeBoardDto fbDto) {
		BoardDto board = new BoardDto();
		board.setBoardTypeId(1);
		board.setName(fbDto.getName());
		board.setTitle(fbDto.getTitle());
		boardMapper.insertBoard(board);
		boardMapper.insertFreeBoard(fbDto);
		List<FileInfoDto> fileInfos = fbDto.getFileInfos();
		
		if (fileInfos != null && !fileInfos.isEmpty()) {
			boardMapper.registFileInfo(fbDto);
		}

		
	}

}
