package com.ssafy.board.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class FreeBoardDto extends BoardDto{
	private String content;
}
