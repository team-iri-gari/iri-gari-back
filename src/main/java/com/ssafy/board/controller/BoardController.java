package com.ssafy.board.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.FreeBoardDto;
import com.ssafy.board.model.service.BoardService;

@CrossOrigin("*")
@RestController
@RequestMapping("/board")
public class BoardController {
	private final BoardService boardService;

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping("free")
	public ResponseEntity<List<FreeBoardDto>> showFbList() {
		return ResponseEntity.ok().body(boardService.selectFreeBoard());
	}
	
	@GetMapping("{keyword}")
	public ResponseEntity<List<FreeBoardDto>> searchFbList(@PathVariable String keyword) {
		return ResponseEntity.ok().body(boardService.searchFreeBoard(keyword));
	}
}
