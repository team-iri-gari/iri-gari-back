package com.ssafy.irigari.board.model;

import lombok.Data;

@Data
public class BoardDto {
	private int articleId;
	private String title;
	private String img;
	private String name;
	private int boardTypeId;
	private String regDate;	
}
