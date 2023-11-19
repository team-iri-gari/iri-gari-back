package com.ssafy.board.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class FreeBoardDto extends BoardDto{
	private String content;
//	원래 여기 있던걸 board로 옮길게
//	private List<FileInfoDto> fileInfos;
}
