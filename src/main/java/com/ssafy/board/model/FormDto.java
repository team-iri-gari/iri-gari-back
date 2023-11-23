package com.ssafy.board.model;

import java.util.List;

import lombok.Data;

@Data
public class FormDto {
	private String name;
	private String title;
	private List<Integer> placeIdx;
	private List<String> placeName;
	private List<String> date;
	private List<String> timeStart;
	private List<String> timeEnd;
	private List<String> description;
	private List<String> placeX;
	private List<String> placeY;
}
