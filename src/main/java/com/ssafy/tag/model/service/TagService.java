package com.ssafy.tag.model.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ssafy.tag.model.TagDto;

public interface TagService {

	List<TagDto> recommend();

}
