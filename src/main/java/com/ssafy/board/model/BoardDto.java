package com.ssafy.board.model;

import java.util.List;

import lombok.Data;

@Data
public class BoardDto {
	private int articleId;
	private String title;
	private String img;
	private String name;
	private int boardTypeId;
	private String regDate;
	private List<FileInfoDto> fileInfos;
	private List<String> tagList;
}
