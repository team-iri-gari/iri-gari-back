package com.ssafy.board.model;

import java.sql.Time;
import java.sql.Date;

import lombok.Data;

@Data
public class PlanBoardDto{
	private int articleId;
	private int planIdx;
	private String imgSrc;
	private String imgId;
	private String placeName;
	private String placeId;
	private Date date;
	private Time timeStart;
	private Time timeEnd;
	private String description;
}