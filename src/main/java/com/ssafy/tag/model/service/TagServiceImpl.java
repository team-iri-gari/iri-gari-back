package com.ssafy.tag.model.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ssafy.tag.model.TagDto;
import com.ssafy.tag.model.mapper.TagMapper;

@Service
public class TagServiceImpl implements TagService {

	private final TagMapper tagMapper;

	public TagServiceImpl(TagMapper tagMapper) {
		super();
		this.tagMapper = tagMapper;
	}

	@Override
	public List<TagDto> recommend() {
		return tagMapper.selectRecommendTags();
	}

}
