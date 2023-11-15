package com.ssafy.board.model.service;

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
	public List<FreeBoardDto> selectFreeBoard() {
		return boardMapper.selectFreeBoard();
	}

	@Override
	public List<FreeBoardDto> searchFreeBoard(String keyword) {
		return boardMapper.searchFreeBoard(keyword);
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
