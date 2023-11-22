package com.ssafy.board.controller;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.FileInfoDto;
import com.ssafy.board.model.FormDto;
import com.ssafy.board.model.FreeBoardDto;
import com.ssafy.board.model.PlanBoardDto;
import com.ssafy.board.model.service.BoardService;

@CrossOrigin("*")
@RestController
@RequestMapping("/board")
public class BoardController {

	private final BoardService boardService;

	@Autowired
	private AmazonS3 amazonS3Client;

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping("{type}")
	public ResponseEntity<List<?>> showBoardList(@PathVariable String type) {
		return ResponseEntity.ok().body(boardService.selectBoardType(type));
	}

	@GetMapping("free/{id}")
	public ResponseEntity<FreeBoardDto> showfreePost(@PathVariable int id) {
		return ResponseEntity.ok().body(boardService.selectFreeBoardId(id));
	}
	
	@GetMapping("plan/{id}")
	public ResponseEntity<List<PlanBoardDto>> showplanPost(@PathVariable int id) {
		return ResponseEntity.ok().body(boardService.selectPlanBoardId(id));
	}

	@GetMapping("/photo/{id}")
	public ResponseEntity<List<FileInfoDto>> getPhotos(@PathVariable int id) {
		List<FileInfoDto> photos = boardService.getPhotosByPostId(id);
		return ResponseEntity.ok().body(photos);
	}

	@GetMapping("search/{type}")
	public ResponseEntity<List<?>> searchFbList(@PathVariable("type") String type,
			@RequestParam("keyword") String keyword) {
		return ResponseEntity.ok().body(boardService.searchBoard(type, keyword));
	}

	@PostMapping("write/free")
	public ResponseEntity<String> writeFreeBoard(FreeBoardDto freeBoard, @RequestParam("upfile") MultipartFile[] files, @RequestParam("tagList") String tagList) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		freeBoard.setTagList(mapper.readValue(tagList, List.class));
		
		
		if (files[0] != null && !files[0].isEmpty()) {
	        String today = new SimpleDateFormat("yyMMdd").format(new Date());
	        String bucketName = "iri-gari-image-server";

	        List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
	        for (MultipartFile mfile : files) {
	            FileInfoDto fileInfoDto = new FileInfoDto();
	            String originalFileName = mfile.getOriginalFilename();
	            if (originalFileName != null && !originalFileName.isEmpty()) {
	                String saveFileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf('.'));

	                ObjectMetadata metadata = new ObjectMetadata();
	                metadata.setContentLength(mfile.getSize());
	                amazonS3Client.putObject(new PutObjectRequest(bucketName, today + "/" + saveFileName, mfile.getInputStream(), metadata));

	                fileInfoDto.setSaveFolder(today);
	                fileInfoDto.setOriginalFile(originalFileName);
	                fileInfoDto.setSaveFile(saveFileName);
	            }
	            fileInfos.add(fileInfoDto);
	        }
	        freeBoard.setFileInfos(fileInfos);
	        
	        System.out.println(today + "/" + freeBoard.getFileInfos().get(0).getSaveFile());
	        
	        freeBoard.setImg(today + "/" + freeBoard.getFileInfos().get(0).getSaveFile());
	    }
		freeBoard.setBoardTypeId(1);
	    boardService.insertFreeBoard(freeBoard);
	    return ResponseEntity.ok("OK");
	}

	@PostMapping(value = "write/plan", consumes = "multipart/form-data")
	public void writePlanBoard(@ModelAttribute FormDto fdto, @RequestParam("name") String name,
			@RequestParam("title") String title, @RequestParam("upfile") MultipartFile[] files) throws Exception {
		BoardDto board = new BoardDto();
		String realName = URLDecoder.decode(name, StandardCharsets.UTF_8.toString());
		System.out.println(realName);
		board.setName(realName);
		board.setTitle(title);
		board.setBoardTypeId(2);
		boardService.insertPlanBoard(board, fdto, files);
	}
}
