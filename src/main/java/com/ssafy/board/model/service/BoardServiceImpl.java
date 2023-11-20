package com.ssafy.board.model.service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.FileInfoDto;
import com.ssafy.board.model.FormDto;
import com.ssafy.board.model.FreeBoardDto;
import com.ssafy.board.model.PlanBoardDto;
import com.ssafy.board.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;
	
	@Autowired
	private AmazonS3 amazonS3Client;
	
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Override
	public List<BoardDto> selectAllBoard() {
		return boardMapper.selectAllBoard();
	}

	@Override
	public List<?> selectBoardType(String type) {
		// 받아오는 보드 타입 문자열에 따라 각 유형 보드 반환
		if (type.equals("free")) {
			return boardMapper.selectFreeBoard();			
		} else if (type.equals("plan")) {
			return boardMapper.selectPlanBoard();
		} else return null;
	}

	@Override
	public List<?> searchBoard(String type, String keyword) {
		String[] words = keyword.split(" "); 
		System.out.println(Arrays.toString(words));
		
		if (type.equals("free")) {
			return boardMapper.searchFreeBoard(words);			
		} else if (type.equals("plan")) {
			return boardMapper.searchPlanBoard(words);
		}
		return null;
	}

	@Override
	@Transactional
	public void insertFreeBoard(FreeBoardDto fbDto) {
		BoardDto board = new BoardDto();
		board.setBoardTypeId(1);
		board.setName(fbDto.getName());
		board.setTitle(fbDto.getTitle());
		boardMapper.insertBoard(board);
		fbDto.setArticleId(board.getArticleId());
		boardMapper.insertFreeBoard(fbDto);
		List<FileInfoDto> fileInfos = fbDto.getFileInfos();
		
		if (fileInfos != null && !fileInfos.isEmpty()) {
			boardMapper.registFileInfo(fbDto);
		}
	}

	@Override
	@Transactional
	public void insertPlanBoard(BoardDto board, FormDto fdto, MultipartFile[] files) throws Exception {
		List<PlanBoardDto> plist = new ArrayList<>();
		List<FileInfoDto> flist = new ArrayList<>();
		int size = fdto.getPlaceIdx().size();
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
        String bucketName = "iri-gari-image-server";
        
        
		for (int i = 0; i < size; i++) {
			// 파일 업로드
			MultipartFile mfile = files[i];
			FileInfoDto fileInfoDto = new FileInfoDto();
			String originalFileName = mfile.getOriginalFilename();
			String saveFileName = null;
            if (originalFileName != null && !originalFileName.isEmpty()) {
                saveFileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf('.'));

                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(mfile.getSize());
                amazonS3Client.putObject(new PutObjectRequest(bucketName, today + "/" + saveFileName, mfile.getInputStream(), metadata));

                fileInfoDto.setSaveFolder(today);
                fileInfoDto.setOriginalFile(originalFileName);
                fileInfoDto.setSaveFile(saveFileName);
            }
            flist.add(fileInfoDto);
            board.setFileInfos(flist);			
			
			PlanBoardDto pb = new PlanBoardDto();
			pb.setPlanIdx(i);
			pb.setImgSrc(today);	// 해당 계획 카드의 이미지 경로 저장
			pb.setImgId(saveFileName);	// 해당 계획 카드의 이미지 업로드 아이디 저장
			pb.setPlaceName(fdto.getPlaceName().get(i));
			pb.setPlaceId(fdto.getPlaceId().get(i));
			String dateStr = fdto.getDate().get(i);
			String stimeStr = fdto.getTimeStart().get(i);
			String etimeStr = fdto.getTimeEnd().get(i);
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date sqlDate = new java.sql.Date(formatter.parse(dateStr).getTime());
			formatter = new SimpleDateFormat("HH:mm");
			Time stime = new Time(formatter.parse(stimeStr).getTime());
			Time etime = new Time(formatter.parse(etimeStr).getTime());
			pb.setDate(sqlDate);
			pb.setTimeStart(stime);
			pb.setTimeEnd(etime);
			pb.setDescription(fdto.getDescription().get(i));
			
			plist.add(pb);
		}
		
		boardMapper.insertBoard(board);
		boardMapper.insertPlanBoard(plist, board.getArticleId());
		boardMapper.registFileInfo(board);
	}
	
	@Override
	public FreeBoardDto selectFreeBoardId(int id) {
		return boardMapper.selectFreeBoardId(id);
	}

	@Override
	public List<PlanBoardDto> selectPlanBoardId(int id) {
		return boardMapper.selectPlanBoardId(id);
	}
	
	@Override
	public List<FileInfoDto> getPhotosByPostId(int postId) {
        return boardMapper.getPhotosByPostId(postId);
    }



}
